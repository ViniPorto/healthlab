package com.porto.HealthLabApi.app.authentication.providers.repositories.models.converters;

import java.util.List;

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

        userEntity.setCode(userModel.getCode());
        userEntity.setName(userModel.getName());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setLogin(userModel.getLogin());
        userEntity.setActive(userModel.getActive());
        userEntity.setAdministrator(userModel.getAdministrator());

        return userEntity;
    }

    public List<UserEntity> mapToListEntity(List<UserModel> listUserModel) {
        return listUserModel.stream().map(this::mapToEntity).toList();
    }

}
