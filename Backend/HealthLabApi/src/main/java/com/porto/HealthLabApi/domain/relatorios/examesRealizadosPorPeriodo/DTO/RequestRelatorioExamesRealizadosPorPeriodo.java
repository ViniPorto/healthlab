package com.porto.HealthLabApi.domain.relatorios.examesRealizadosPorPeriodo.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record RequestRelatorioExamesRealizadosPorPeriodo(
    @NotNull
    LocalDate dataInicial,
    @NotNull
    LocalDate dataFinal
) {
    
}
