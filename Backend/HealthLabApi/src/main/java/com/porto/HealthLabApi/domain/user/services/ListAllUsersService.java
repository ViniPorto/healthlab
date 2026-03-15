package com.porto.HealthLabApi.domain.user.services;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.user.dataproviders.UserDataProvider;
import com.porto.HealthLabApi.domain.user.entities.UserEntity;
import com.porto.HealthLabApi.domain.user.usecases.ListAllUsersUsecase;

import lombok.AllArgsConstructor;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@AllArgsConstructor
public class ListAllUsersService implements ListAllUsersUsecase {

    private final UserDataProvider userDataProvider;

    @Override
    public List<UserEntity> execute() {
        return this.userDataProvider.listAllUsers();
    }
    
}
