package com.porto.HealthLabApi.domain.pessoa.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RequestCadastrarPessoa(
    @NotBlank
    @Size(max = 50)
    String nome,
    @NotBlank
    @Pattern(regexp = "\\d{11}")
    String cpf,
    @NotBlank
    @Email
    @Size(max = 50)
    String email,
    @NotBlank
    @Pattern(regexp = "\\d{11}")
    String telefone,
    @Size(max = 300)
    String dadosGerais, 
    @NotNull
    @Past
    LocalDate dataNascimento,
    @Size(max = 300)
    String observacao
) {

}
