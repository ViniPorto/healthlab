package com.porto.HealthLabApi.domain.user.entities;

import lombok.Data;

@Data
public class UserEntity {

    private Long code;
    private String login;
    private String password;
    private Boolean active;
    private Boolean administrator;
    private String name;

}
