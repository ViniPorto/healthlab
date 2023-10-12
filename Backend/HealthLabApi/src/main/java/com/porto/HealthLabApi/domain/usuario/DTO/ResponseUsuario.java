package com.porto.HealthLabApi.domain.usuario.DTO;

import java.util.ArrayList;
import java.util.List;

import com.porto.HealthLabApi.domain.usuario.Usuario;

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

    public static List<ResponseUsuario> toListResponseUsuario(List<Usuario> usuarios){
        List<ResponseUsuario> novaLista = new ArrayList<>();
        for(Usuario usuario : usuarios){
            novaLista.add(new ResponseUsuario(usuario));
        }
        return novaLista;
    }

}
