package com.porto.HealthLabApi.domain.metodo.DTO;

import com.porto.HealthLabApi.domain.metodo.Metodo;

public record ResponseMetodo(
    Long metodoId,
    String metodoNome,
    String metodoDescricao
) {
    public ResponseMetodo(Metodo metodo){
        this(metodo.getId(), metodo.getNome(), metodo.getDescricao());
    }
}
