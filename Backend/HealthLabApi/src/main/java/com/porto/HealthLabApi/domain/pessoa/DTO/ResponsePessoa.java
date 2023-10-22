package com.porto.HealthLabApi.domain.pessoa.DTO;

import java.time.LocalDate;

import com.porto.HealthLabApi.domain.pessoa.Pessoa;

public record ResponsePessoa(
    Long pessoaId,
    String pessoaNome,
    String pessoaCPF,
    String pessoaEmail,
    String pessoaTelefone,
    String pessoaDadosGerais,
    LocalDate pessoaDataNascimento,
    String pessoaObservacao
) {

    public ResponsePessoa(Pessoa pessoa) {
        this(pessoa.getId(), pessoa.getNome(), pessoa.getCpf(), pessoa.getEmail(), pessoa.getTelefone(), pessoa.getDadosGerais(), pessoa.getDataNascimento(), pessoa.getObservacao());
    }
    
}
