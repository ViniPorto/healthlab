package com.porto.HealthLabApi.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.porto.HealthLabApi.repositories.UsuarioRepository;
import com.porto.HealthLabApi.services.TokenService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter  {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ResponseHandler responseHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            var tokenJWT = recuperarToken(request);

            if(tokenJWT != null){
                var subject = tokenService.getSubject(tokenJWT);
                var usuario = usuarioRepository.findByLogin(subject);

                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) { //Caso o Token esteja expirado ou inválido
            System.out.println(e);
            
            var map = responseHandler.generateMap("Token expirado ou invalido", false, HttpStatus.FORBIDDEN, null);
            var mapper = new ObjectMapper();
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write(mapper.writeValueAsString(map));
        }
        
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
    
}
