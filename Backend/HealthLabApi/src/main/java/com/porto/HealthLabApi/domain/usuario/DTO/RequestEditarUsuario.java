package com.porto.HealthLabApi.domain.usuario.DTO;

import jakarta.validation.constraints.NotNull;

public record RequestEditarUsuario(
    @NotNull
    Long id,
    String login,
    String senha,
    String nome
) {

}
