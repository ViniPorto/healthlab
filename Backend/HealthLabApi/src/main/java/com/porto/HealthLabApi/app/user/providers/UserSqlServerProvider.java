package com.porto.HealthLabApi.app.user.providers;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.porto.HealthLabApi.app.authentication.providers.repositories.models.UserModel;
import com.porto.HealthLabApi.app.authentication.providers.repositories.models.converters.UserModelConverter;
import com.porto.HealthLabApi.app.user.providers.repositories.UserSqlServerRepository;
import com.porto.HealthLabApi.domain.user.dataproviders.UserDataProvider;
import com.porto.HealthLabApi.domain.user.entities.UserEntity;

import lombok.AllArgsConstructor;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@AllArgsConstructor
public class UserSqlServerProvider implements UserDataProvider {

    private final UserSqlServerRepository userSqlServerRepository;

    private final UserModelConverter userModelConverter;
    
    @Override
    public List<UserEntity> listAllUsers() {
        List<UserModel> usersModel = this.userSqlServerRepository.listAllUsers();

        return this.userModelConverter.mapToListEntity(usersModel);
    }
    
}
