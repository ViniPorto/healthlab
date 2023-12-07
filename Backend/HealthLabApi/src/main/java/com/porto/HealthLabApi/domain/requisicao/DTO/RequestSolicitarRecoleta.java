package com.porto.HealthLabApi.domain.requisicao.DTO;

import jakarta.validation.constraints.NotNull;

public record RequestSolicitarRecoleta(
    @NotNull
    Long requisicaoExameId,
    @NotNull
    Long motivoRecoletaId
) {

}
