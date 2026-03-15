package com.porto.HealthLabApi.app.authentication.providers.repositories.models;

import lombok.Data;

@Data
public class UserModel {
    
    private Long code;
    private String login;
    private String password;
    private Boolean active;
    private Boolean administrator;
    private String name;

}
