package com.porto.HealthLabApi.app.authentication.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.app.authentication.controllers.restModels.LoginRestModel;
import com.porto.HealthLabApi.app.authentication.controllers.restModels.converters.LoginRestModelConverter;
import com.porto.HealthLabApi.app.configurations.ResponseBuilderApi;
import com.porto.HealthLabApi.app.configurations.ResponseBuilderApi.ResponseObj;
import com.porto.HealthLabApi.domain.authentication.entities.LoginEntity;
import com.porto.HealthLabApi.domain.authentication.exceptions.InvalidUsernameOrPasswordException;
import com.porto.HealthLabApi.domain.authentication.usecases.GenerateTokenUsecase;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AuthenticationController {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());

    private static final String ERROR_MESSAGE = "Erro inesperado ao realizar autenticação: %s";
    private static final String SUCCESS_MESSAGE = "Sucesso ao realizar autenticação";

    private final ResponseBuilderApi responseBuilderApi;

    private final LoginRestModelConverter loginRestModelConverter;    

    private final GenerateTokenUsecase generateTokenUsecase;
    
    @Operation(summary = "Realiza autenticação do usuário e retorna um JWT")
    @PostMapping
    public ResponseEntity<ResponseObj<String>> authenticate(@RequestBody @Valid LoginRestModel restModel) {
        try {
            LoginEntity loginEntity = this.loginRestModelConverter.mapToEntity(restModel);

            String token = this.generateTokenUsecase.execute(loginEntity);

            return this.responseBuilderApi.generateResponse(SUCCESS_MESSAGE, true, null, HttpStatus.CREATED, token);
        } catch (InvalidUsernameOrPasswordException e) {
            return this.responseBuilderApi.generateResponse(null, false, e.getMessage(), HttpStatus.BAD_REQUEST, null);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            return this.responseBuilderApi.generateResponse(null, false, String.format(ERROR_MESSAGE, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
