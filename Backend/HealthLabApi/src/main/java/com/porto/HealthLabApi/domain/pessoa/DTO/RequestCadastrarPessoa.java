package com.porto.HealthLabApi.domain.pessoa.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public record RequestCadastrarPessoa(
    @NotBlank
    String nome,
    @NotBlank
    @Pattern(regexp = "\\d{11}")
    String CPF,
    @NotBlank
    @Email
    String email,
    @NotBlank
    @Pattern(regexp = "\\d{11}")
    String telefone,
    String dadosGerais, 
    @NotNull
    @Past
    LocalDate dataNascimento,
    String observacao
) {

}
