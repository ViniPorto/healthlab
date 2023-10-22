package com.porto.HealthLabApi.domain.medico.DTO;

import com.porto.HealthLabApi.domain.medico.MedicoUF;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RequestCadastrarMedico(
    @NotBlank
    @Size(max = 50)
    String nome,
    @NotBlank
    @Pattern(regexp = "^(\\d{5}|\\d{6})$")
    String crm,
    @NotNull
    @Enumerated
    MedicoUF uf,
    @Pattern(regexp = "\\d{11}")
    String telefone,
    @Email
    @Size(max = 50)
    String email
) {

}
