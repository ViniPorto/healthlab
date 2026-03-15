package com.porto.HealthLabApi.app.authentication.controllers.restModels.converters;

import org.springframework.stereotype.Component;

import com.porto.HealthLabApi.app.authentication.controllers.restModels.LoginRestModel;
import com.porto.HealthLabApi.domain.authentication.entities.LoginEntity;

@Component
public class LoginRestModelConverter {
    
    public LoginEntity mapToEntity(LoginRestModel restModel) {
        LoginEntity loginEntity = new LoginEntity();

        loginEntity.setUsername(restModel.getUsername());
        loginEntity.setPassword(restModel.getPassword());

        return loginEntity;
    }
    
}
