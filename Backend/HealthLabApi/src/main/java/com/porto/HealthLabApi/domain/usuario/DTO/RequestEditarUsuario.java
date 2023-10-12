package com.porto.HealthLabApi.domain.usuario.DTO;

public record RequestEditarUsuario(
    String login,
    String senha,
    String nome
) {

}
