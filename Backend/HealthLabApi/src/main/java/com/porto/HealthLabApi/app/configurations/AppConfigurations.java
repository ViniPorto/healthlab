package com.porto.HealthLabApi.app.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class AppConfigurations {
    
    @Value("${HEALTHLAB_API_SECRET}")
    private String apiSecret;

    @Value("${HEALTHLAB_API_TTL_TOKEN}")
    private Long ttlToken;

}
