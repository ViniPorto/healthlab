package com.porto.HealthLabApi.app.authentication.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.app.authentication.controllers.restModels.LoginRestModel;
import com.porto.HealthLabApi.app.authentication.controllers.restModels.converters.LoginRestModelConverter;
import com.porto.HealthLabApi.app.configurations.ResponseHandlerConfig;
import com.porto.HealthLabApi.app.configurations.ResponseHandlerConfig.ResponseObj;
import com.porto.HealthLabApi.domain.authentication.entities.LoginEntity;
import com.porto.HealthLabApi.domain.authentication.exceptions.InvalidUsernameOrPasswordException;
import com.porto.HealthLabApi.domain.authentication.usecases.GenerateTokenUsecase;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AuthenticationController {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());

    private static final String ERROR_MESSAGE = "Erro inesperado ao realizar autenticação: %s";
    private static final String SUCCESS_MESSAGE = "Sucesso ao realizar autenticação";

    private final ResponseHandlerConfig responseHandlerConfig;

    private final LoginRestModelConverter loginRestModelConverter;    

    private final GenerateTokenUsecase generateTokenUsecase;
    
    @PostMapping
    public ResponseEntity<ResponseObj> authenticate(@Valid LoginRestModel restModel) {
        try {
            LoginEntity loginEntity = this.loginRestModelConverter.mapToEntity(restModel);

            String token = this.generateTokenUsecase.execute(loginEntity);

            return this.responseHandlerConfig.generateResponse(SUCCESS_MESSAGE, true, null, null, token);
        } catch (InvalidUsernameOrPasswordException e) {
            return this.responseHandlerConfig.generateResponse(null, false, e.getMessage(), HttpStatus.BAD_REQUEST, null);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            return this.responseHandlerConfig.generateResponse(null, false, String.format(ERROR_MESSAGE, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
