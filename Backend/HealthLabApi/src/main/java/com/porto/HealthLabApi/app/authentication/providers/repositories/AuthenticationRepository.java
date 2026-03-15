package com.porto.HealthLabApi.app.authentication.providers.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.app.authentication.providers.repositories.models.UserModel;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class AuthenticationRepository {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationRepository.class.getName());

    private static final String QUERY_SEARCH_USER_BY_USERNAME = "SELECT USU_CODIGO, USU_USERNAME, USU_SENHA, USU_NOME FROM T_USUARIO WHERE USU_USERNAME = ?";
    
    private final DataSource dataSource;

    public UserModel getUserEntityByUsername(String username) {
        try (Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(QUERY_SEARCH_USER_BY_USERNAME)) {

            preparedStatement.setString(1, username);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {

                    UserModel userModel = new UserModel();

                    userModel.setId(rs.getLong("USU_CODIGO"));
                    userModel.setUsername(rs.getString("USU_USERNAME"));
                    userModel.setPassword(rs.getString("USU_SENHA"));
                    userModel.setName(rs.getString("USU_NOME"));

                    return userModel;
                }

                return null;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            return null;
        }
        
    }

}
