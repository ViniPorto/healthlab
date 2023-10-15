package com.porto.HealthLabApi.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.porto.HealthLabApi.infra.exception.TratadorDeErros;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/usuario").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/usuario").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/usuario/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/usuario/inativar/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/usuario/reativar/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/usuario/deletar/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/usuario/elegerAdministrador/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(new TratadorDeErros())
                .and().build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
