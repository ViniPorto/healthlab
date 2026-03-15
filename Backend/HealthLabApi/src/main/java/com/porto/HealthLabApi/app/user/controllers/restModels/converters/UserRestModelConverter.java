package com.porto.HealthLabApi.app.user.controllers.restModels.converters;

import java.util.List;

import org.springframework.stereotype.Component;

import com.porto.HealthLabApi.app.user.controllers.restModels.UserRestModel;
import com.porto.HealthLabApi.domain.user.entities.UserEntity;

@Component
public class UserRestModelConverter {
    
    public UserRestModel mapToRestModel(UserEntity userEntity) {
        UserRestModel userRestModel = new UserRestModel();

        userRestModel.setCode(userEntity.getCode());
        userRestModel.setLogin(userEntity.getLogin());
        userRestModel.setName(userEntity.getName());
        userRestModel.setActive(userEntity.getActive());
        userRestModel.setAdministrator(userEntity.getAdministrator());

        return userRestModel;
    }

    public List<UserRestModel> mapToListRestModel(List<UserEntity> listUserEntity) {
        return listUserEntity.stream().map(this::mapToRestModel).toList();
    }

}
