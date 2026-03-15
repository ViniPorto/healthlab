package com.porto.HealthLabApi.app.authentication.controllers.restModels;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRestModel {
    
    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
