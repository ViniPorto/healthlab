package com.porto.HealthLabApi.domain.authentication.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.authentication.dataproviders.AuthenticationDataProvider;
import com.porto.HealthLabApi.domain.authentication.entities.LoginEntity;
import com.porto.HealthLabApi.domain.authentication.exceptions.InvalidUsernameOrPasswordException;
import com.porto.HealthLabApi.domain.authentication.usecases.GenerateJWTUsecase;
import com.porto.HealthLabApi.domain.authentication.usecases.GenerateTokenUsecase;
import com.porto.HealthLabApi.domain.user.entities.UserEntity;

import lombok.AllArgsConstructor;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@AllArgsConstructor
public class GenerateTokenService implements GenerateTokenUsecase {

    private static final String ERROR_MESSAGE = "Usuário ou senha inválida.";

    private final GenerateJWTUsecase generateJWTUsecase;
    private final AuthenticationDataProvider authenticationDataProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String execute(LoginEntity loginEntity) throws InvalidUsernameOrPasswordException {
        
        UserEntity userEntity = this.authenticationDataProvider.getUserEntityByLogin(loginEntity.getUsername());

        if(userEntity == null) {
            throw new InvalidUsernameOrPasswordException(ERROR_MESSAGE);
        }

        if(!this.passwordEncoder.matches(loginEntity.getPassword(), userEntity.getPassword())) {
            throw new InvalidUsernameOrPasswordException(ERROR_MESSAGE);
        }

        return this.generateJWTUsecase.execute(userEntity);

    }
    
}
