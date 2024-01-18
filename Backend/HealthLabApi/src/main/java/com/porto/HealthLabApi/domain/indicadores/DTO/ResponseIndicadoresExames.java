package com.porto.HealthLabApi.domain.indicadores.DTO;

import java.time.LocalDateTime;

public record ResponseIndicadoresExames(
    Long indicadoresRequisicaoId,
    String indicadoresPacienteNome,
    String indicadoresExameTitulo,
    LocalDateTime indicadoresHoraPrevisao,
    Long indicadoresDeltaMinutos,
    String indicadoresStatus
) {
    
}
