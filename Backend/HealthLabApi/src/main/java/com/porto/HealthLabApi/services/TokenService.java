package com.porto.HealthLabApi.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.infra.exception.exceptions.TokenJWTInvalidoOuExpiradoException;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    
    public String gerarToken(Usuario usuario){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("HealthLab API")
                .withSubject(usuario.getLogin())
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar Token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                .withIssuer("HealthLab API")
                .build()
                .verify(tokenJWT)
                .getSubject();
        } catch (JWTVerificationException exception){
            throw new TokenJWTInvalidoOuExpiradoException();
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
