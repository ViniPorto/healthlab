package com.porto.HealthLabApi.domain.layout.DTO;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record RequestCadastrarLayout(
    @NotNull
    Long exameId,
    @NotNull
    List<RequestCadastrarLayoutCampos> campos
) {

}
