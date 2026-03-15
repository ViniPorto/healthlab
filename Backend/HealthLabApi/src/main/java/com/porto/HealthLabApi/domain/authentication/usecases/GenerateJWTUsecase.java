package com.porto.HealthLabApi.domain.authentication.usecases;

import com.porto.HealthLabApi.domain.user.entities.UserEntity;

public interface GenerateJWTUsecase {
    
    String execute(UserEntity userEntity);

}
