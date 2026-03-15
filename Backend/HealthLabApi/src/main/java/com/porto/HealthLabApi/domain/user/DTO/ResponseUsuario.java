package com.porto.HealthLabApi.domain.user.DTO;

import com.porto.HealthLabApi.domain.user.Usuario;

public record ResponseUsuario(
    Long usuarioId,
    String usuarioNome,
    String usuarioLogin,
    boolean usuarioAtivo,
    boolean usuarioAdministrador
) {

    public ResponseUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.isAtivo(), usuario.isAdministrador());
    }

}
