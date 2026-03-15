package com.porto.HealthLabApi.app.authentication.controllers.restModels;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRestModel {
    
    @Schema(example = "admin")
    @NotBlank
    private String username;
    @Schema(example = "admin")
    @NotBlank
    private String password;

}
