package com.porto.HealthLabApi.app.authentication.providers;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.porto.HealthLabApi.app.authentication.providers.repositories.AuthenticationRepository;
import com.porto.HealthLabApi.app.authentication.providers.repositories.models.UserModel;
import com.porto.HealthLabApi.app.authentication.providers.repositories.models.converters.UserModelConverter;
import com.porto.HealthLabApi.domain.authentication.dataproviders.AuthenticationDataProvider;
import com.porto.HealthLabApi.domain.user.entities.UserEntity;

import lombok.AllArgsConstructor;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //Revisar
@AllArgsConstructor
public class AuthenticationProvider implements AuthenticationDataProvider {

    private final AuthenticationRepository authenticationRepository;
    private final UserModelConverter userModelConverter;

    @Override
    public UserEntity getUserEntityByLogin(String username) {
        UserModel userModel = this.authenticationRepository.getUserEntityByUsername(username);

        return this.userModelConverter.mapToEntity(userModel);
    }
        
    
}
