package com.porto.HealthLabApi.domain.relatorios.examesRealizadosPorPeriodo.DTO;

import java.time.LocalDate;
import java.util.List;

public record ResponseRelatorioExamesRealizadosPorPeriodo(
    LocalDate data,
    Long numeroExamesRealizados,
    List<ResponseRelatorioExamesRealizadosPorPeriodoExames> listaExames
) {
    
}
