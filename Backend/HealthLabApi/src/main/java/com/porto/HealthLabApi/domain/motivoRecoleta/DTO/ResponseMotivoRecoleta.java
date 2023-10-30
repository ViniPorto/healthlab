package com.porto.HealthLabApi.domain.motivoRecoleta.DTO;

import com.porto.HealthLabApi.domain.motivoRecoleta.MotivoRecoleta;

public record ResponseMotivoRecoleta(
    Long motivoRecoletaId,
    String motivoRecoletaNome,
    String motivoRecoletaDescricao
) {
    public ResponseMotivoRecoleta(MotivoRecoleta motivoRecoleta){
        this(motivoRecoleta.getId(), motivoRecoleta.getNome(), motivoRecoleta.getDescricao());
    }
}
