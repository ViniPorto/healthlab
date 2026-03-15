package com.porto.HealthLabApi.app.authentication.providers.repositories.models;

import lombok.Data;

@Data
public class UserModel {
    
    private Long id;
    private String username;
    private String password;
    private String name;

}
