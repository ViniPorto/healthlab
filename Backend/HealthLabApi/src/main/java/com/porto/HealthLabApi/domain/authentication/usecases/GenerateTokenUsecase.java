package com.porto.HealthLabApi.domain.authentication.usecases;

import com.porto.HealthLabApi.domain.authentication.entities.LoginEntity;
import com.porto.HealthLabApi.domain.authentication.exceptions.InvalidUsernameOrPasswordException;

public interface GenerateTokenUsecase {
    
    String execute(LoginEntity loginEntity) throws InvalidUsernameOrPasswordException;

}
