package com.porto.HealthLabApi.app.user.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.app.configurations.ResponseBuilderApi;
import com.porto.HealthLabApi.app.configurations.ResponseBuilderApi.ResponseObj;
import com.porto.HealthLabApi.app.user.controllers.restModels.UserRestModel;
import com.porto.HealthLabApi.app.user.controllers.restModels.converters.UserRestModelConverter;
import com.porto.HealthLabApi.domain.user.entities.UserEntity;
import com.porto.HealthLabApi.domain.user.usecases.ListAllUsersUsecase;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    private static final String ERROR_MESSAGE = "Erro inesperado ao listar usuários: %s";
    private static final String SUCCESS_MESSAGE = "Consulta realizada com sucesso";
    
    private final ResponseBuilderApi responseBuilderApi;

    private final ListAllUsersUsecase listAllUsersUsecase;

    private final UserRestModelConverter userRestModelConverter;

    @Operation(summary = "Retorna todos os usuários cadastrados")
    @GetMapping
    public ResponseEntity<ResponseObj<List<UserRestModel>>> listarUsuarios(){
        try {
            List<UserEntity> usersEntity = this.listAllUsersUsecase.execute();

            return this.responseBuilderApi.generateResponse(SUCCESS_MESSAGE, true, null, HttpStatus.OK, this.userRestModelConverter.mapToListRestModel(usersEntity));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            return this.responseBuilderApi.generateResponse(null, false, String.format(ERROR_MESSAGE, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
