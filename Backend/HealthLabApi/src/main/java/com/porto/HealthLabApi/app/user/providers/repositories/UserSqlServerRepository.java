package com.porto.HealthLabApi.app.user.providers.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.app.authentication.providers.repositories.models.UserModel;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserSqlServerRepository {
    
    private static final Logger LOGGER = Logger.getLogger(UserSqlServerRepository.class.getName());

    private static final String QUERY_SEARCH_ALL_USERS = "SELECT USU_CODIGO, USU_LOGIN, USU_SENHA, USU_ATIVO, USU_ADMINISTRADOR, USU_NOME FROM T_USUARIO";
    
    private final DataSource dataSource;

    public List<UserModel> listAllUsers() {
        try (Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(QUERY_SEARCH_ALL_USERS)) {

            try (ResultSet rs = preparedStatement.executeQuery()) {
                List<UserModel> users = new ArrayList<>();
                while (rs.next()) {

                    UserModel userModel = new UserModel();

                    userModel.setCode(rs.getLong("USU_CODIGO"));
                    userModel.setLogin(rs.getString("USU_LOGIN"));
                    userModel.setPassword(rs.getString("USU_SENHA"));
                    userModel.setActive(rs.getBoolean("USU_ATIVO"));
                    userModel.setAdministrator(rs.getBoolean("USU_ADMINISTRADOR"));
                    userModel.setName(rs.getString("USU_NOME"));

                    users.add(userModel);
                }

                return users;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            return List.of();
        }
        
    }

}
