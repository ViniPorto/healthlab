package com.porto.HealthLabApi.domain.historico.DTO;

import java.time.LocalDateTime;

import com.porto.HealthLabApi.domain.historico.Historico;

public record ResponseHistorico(
    Long referenciaId,
    String tabela,
    String usuarioNome,
    Long usuarioId,
    String acaoRealizada,
    LocalDateTime acaoDataHora,
    String dadosAtualizados
) {

    public ResponseHistorico(Historico historico) {
        this(historico.getId(), historico.getTabela(), historico.getUsuario().getNome(), historico.getUsuario().getId(), historico.getAcaoRealizada(), historico.getAcaoDataHora(), historico.getDadosAtualizados());
    }
    
}
