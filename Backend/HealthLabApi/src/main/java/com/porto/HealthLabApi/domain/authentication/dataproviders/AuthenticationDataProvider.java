package com.porto.HealthLabApi.domain.authentication.dataproviders;

import com.porto.HealthLabApi.domain.user.entities.UserEntity;

public interface AuthenticationDataProvider {
    
    UserEntity getUserEntityByLogin(String username);

}
