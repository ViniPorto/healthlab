package com.porto.HealthLabApi.app.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import com.porto.HealthLabApi.domain.authentication.dto.TokenPayload;
import com.porto.HealthLabApi.domain.authentication.exceptions.InvalidTokenException;
import com.porto.HealthLabApi.domain.authentication.usecases.ValidateTokenUsecase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class InterceptorRestRequests implements HandlerInterceptor {

    private final ValidateTokenUsecase validateTokenUsecase;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String token = authHeader.replace("Bearer ", "");

        TokenPayload tokenPayload = null;

        try {
            tokenPayload = this.validateTokenUsecase.execute(token);
        } catch (InvalidTokenException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            
            return false;
        }

        if (tokenPayload == null || tokenPayload.getUserId() == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        request.setAttribute("authenticatedUser", tokenPayload); //Para usar nos controllers: @AuthenticatedUser TokenPayload tokenPayload

        return true;
    }

}
