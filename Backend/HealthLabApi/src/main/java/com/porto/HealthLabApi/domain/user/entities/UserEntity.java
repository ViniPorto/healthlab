package com.porto.HealthLabApi.domain.user.entities;

import lombok.Data;

@Data
public class UserEntity {
    
    private Long id;
    private String username;
    private String password;
    private String name;

}
