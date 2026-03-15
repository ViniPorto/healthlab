package com.porto.HealthLabApi.domain.authentication.services;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.app.configurations.AppConfigurations;
import com.porto.HealthLabApi.domain.authentication.usecases.GenerateJWTUsecase;
import com.porto.HealthLabApi.domain.user.entities.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@AllArgsConstructor
public class GenerateJWTService implements GenerateJWTUsecase {

    private final AppConfigurations appConfigurations;

    @Override
    public String execute(UserEntity userEntity) {
        Instant now = Instant.now();
        Instant expiration = now.plusMillis(this.appConfigurations.getTtlToken());

        return Jwts.builder()
                .setSubject(userEntity.getLogin())
                .claim("userId", userEntity.getCode())
                .claim("isAdm", userEntity.getAdministrator())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .signWith(
                    Keys.hmacShaKeyFor(this.appConfigurations.getApiSecret().getBytes()),
                    SignatureAlgorithm.HS256
                )
                .compact();
    }
    
}
