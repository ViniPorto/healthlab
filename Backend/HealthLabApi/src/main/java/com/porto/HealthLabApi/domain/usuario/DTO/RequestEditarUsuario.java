package com.porto.HealthLabApi.domain.usuario.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestEditarUsuario(
    @NotNull
    Long id,
    @Size(max = 20)
    String login,
    @Size(max = 50)
    String senha,
    @Size(max = 50)
    String nome
) {

}
