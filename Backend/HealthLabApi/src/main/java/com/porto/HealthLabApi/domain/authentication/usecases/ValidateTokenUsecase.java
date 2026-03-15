package com.porto.HealthLabApi.domain.authentication.usecases;

import com.porto.HealthLabApi.domain.authentication.dto.TokenPayload;
import com.porto.HealthLabApi.domain.authentication.exceptions.InvalidTokenException;

public interface ValidateTokenUsecase {
    
    TokenPayload execute(String token) throws InvalidTokenException;

}
