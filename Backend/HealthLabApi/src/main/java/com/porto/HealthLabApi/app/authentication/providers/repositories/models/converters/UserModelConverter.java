package com.porto.HealthLabApi.app.authentication.providers.repositories.models.converters;

import org.springframework.stereotype.Component;

import com.porto.HealthLabApi.app.authentication.providers.repositories.models.UserModel;
import com.porto.HealthLabApi.domain.user.entities.UserEntity;

@Component
public class UserModelConverter {
    
    public UserEntity mapToEntity(UserModel userModel) {
        if(userModel == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId(userModel.getId());
        userEntity.setName(userModel.getName());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setLogin(userModel.getLogin());

        return userEntity;
    }

}
