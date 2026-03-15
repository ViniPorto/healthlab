package com.porto.HealthLabApi.app.configurations;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    private final AuthenticatedUserArgumentResolver resolver;

    private InterceptorRestRequests interceptorRestRequests;

    public WebConfig(AuthenticatedUserArgumentResolver resolver, InterceptorRestRequests interceptorRestRequests) {
        this.resolver = resolver;
        this.interceptorRestRequests = interceptorRestRequests;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(resolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.interceptorRestRequests)
                .addPathPatterns("/**")
                .excludePathPatterns(
                    "/login",
                    "/error",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                );
    }
    
}
