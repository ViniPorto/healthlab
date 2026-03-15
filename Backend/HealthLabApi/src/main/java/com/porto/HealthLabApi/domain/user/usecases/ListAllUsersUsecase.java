package com.porto.HealthLabApi.domain.user.usecases;

import java.util.List;

import com.porto.HealthLabApi.domain.user.entities.UserEntity;

public interface ListAllUsersUsecase {
    
    List<UserEntity> execute();

}
