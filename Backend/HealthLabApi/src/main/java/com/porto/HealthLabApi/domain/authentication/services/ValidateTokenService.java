package com.porto.HealthLabApi.domain.authentication.services;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.app.configurations.AppConfigurations;
import com.porto.HealthLabApi.domain.authentication.dto.TokenPayload;
import com.porto.HealthLabApi.domain.authentication.exceptions.InvalidTokenException;
import com.porto.HealthLabApi.domain.authentication.usecases.ValidateTokenUsecase;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@AllArgsConstructor
public class ValidateTokenService implements ValidateTokenUsecase {

    private static final Logger LOGGER = Logger.getLogger(ValidateTokenService.class.getName());

    private final AppConfigurations appConfigurations;

    @Override
    public TokenPayload execute(String token) throws InvalidTokenException {
        try {

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(
                        Keys.hmacShaKeyFor(
                            this.appConfigurations.getApiSecret().getBytes(StandardCharsets.UTF_8)
                        )
                    )
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Long userId = claims.get("userId", Long.class);
            String username = claims.getSubject();
            Boolean isAdm = claims.get("isAdm", Boolean.class);

            return new TokenPayload(userId, username, isAdm);

        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);

            throw new InvalidTokenException("Token inválido ou expirado!");
        }
    }
    
}
