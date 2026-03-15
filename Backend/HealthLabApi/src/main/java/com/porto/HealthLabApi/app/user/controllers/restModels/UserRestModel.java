package com.porto.HealthLabApi.app.user.controllers.restModels;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRestModel {
    
    @Schema(example = "1")
    private Long code;
    @Schema(example = "admin")
    private String login;
    @Schema(example = "true")
    private Boolean active;
    @Schema(example = "true")
    private Boolean administrator;
    @Schema(example = "admin")
    private String name;

}
