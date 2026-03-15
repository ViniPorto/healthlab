package com.porto.HealthLabApi.domain.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenPayload {
    
    private Long userId;
    private String username;
    private Boolean isAdm;

}
