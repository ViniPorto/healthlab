package com.porto.HealthLabApi.domain.historico.DTO;

import java.time.LocalDateTime;

import com.porto.HealthLabApi.domain.historico.Historico;

public record ResponseHistorico(
    Long historicoReferenciaId,
    String historicoTabela,
    String historicoUsuarioNome,
    Long historicoUsuarioId,
    String historicoAcaoRealizada,
    LocalDateTime historicoAcaoDataHora,
    String historicoDadosAtualizados
) {

    public ResponseHistorico(Historico historico) {
        this(historico.getId(), historico.getTabela(), historico.getUsuario().getNome(), historico.getUsuario().getId(), historico.getAcaoRealizada(), historico.getAcaoDataHora(), historico.getDadosAtualizados());
    }
    
}
