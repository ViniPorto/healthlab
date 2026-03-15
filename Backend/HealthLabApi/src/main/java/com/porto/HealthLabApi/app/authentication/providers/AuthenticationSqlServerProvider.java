package com.porto.HealthLabApi.app.authentication.providers;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.porto.HealthLabApi.app.authentication.providers.repositories.AuthenticationSqlServerRepository;
import com.porto.HealthLabApi.app.authentication.providers.repositories.models.UserModel;
import com.porto.HealthLabApi.app.authentication.providers.repositories.models.converters.UserModelConverter;
import com.porto.HealthLabApi.domain.authentication.dataproviders.AuthenticationDataProvider;
import com.porto.HealthLabApi.domain.user.entities.UserEntity;

import lombok.AllArgsConstructor;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@AllArgsConstructor
public class AuthenticationSqlServerProvider implements AuthenticationDataProvider {

    private final AuthenticationSqlServerRepository authenticationRepository;
    private final UserModelConverter userModelConverter;

    @Override
    public UserEntity getUserEntityByLogin(String username) {
        UserModel userModel = this.authenticationRepository.getUserByUsername(username);

        return this.userModelConverter.mapToEntity(userModel);
    }
        
    
}
