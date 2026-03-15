package com.porto.HealthLabApi.domain.user.dataproviders;

import java.util.List;

import com.porto.HealthLabApi.domain.user.entities.UserEntity;

public interface UserDataProvider {
    
    List<UserEntity> listAllUsers();

}
