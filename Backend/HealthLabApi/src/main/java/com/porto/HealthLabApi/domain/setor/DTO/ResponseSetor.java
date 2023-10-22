package com.porto.HealthLabApi.domain.setor.DTO;

import com.porto.HealthLabApi.domain.setor.Setor;

public record ResponseSetor(
    Long setorId,
    String setorNome,
    String setorDescricao
) {
    public ResponseSetor(Setor setor){
        this(setor.getId(), setor.getNome(), setor.getDescricao());
    }
}
