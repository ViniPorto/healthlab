package com.porto.HealthLabApi.domain.indicadores.DTO;

import java.time.LocalDateTime;

public record ResponseIndicadoresExames(
    Long requisicaoId,
    String pacienteNome,
    String exameTitulo,
    LocalDateTime horaPrevisao,
    Long deltaMinutos,
    String status
) {
    
}
