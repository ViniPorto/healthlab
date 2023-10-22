package com.porto.HealthLabApi.domain.medico.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RequestEditarMedico(
    @NotNull
    Long id,
    @Size(max = 50)
    String nome,
    @Pattern(regexp = "\\d{11}")
    String telefone,
    @Email
    @Size(max = 50)
    String email
) {

}
