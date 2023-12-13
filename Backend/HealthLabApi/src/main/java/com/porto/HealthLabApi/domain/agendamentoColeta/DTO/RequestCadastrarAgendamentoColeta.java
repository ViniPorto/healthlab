package com.porto.HealthLabApi.domain.agendamentoColeta.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record RequestCadastrarAgendamentoColeta(
    @NotNull
    @Future
    LocalDateTime dataHoraColeta,
    @NotNull
    Long pessoaId,
    @NotNull
    Long requisicaoId,
    @NotNull
    Long usuarioId
) {
    
}
