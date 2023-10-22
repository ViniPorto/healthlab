package com.porto.HealthLabApi.domain.pessoa;

import java.time.LocalDate;

import com.porto.HealthLabApi.domain.pessoa.DTO.RequestCadastrarPessoa;
import com.porto.HealthLabApi.domain.pessoa.DTO.RequestEditarPessoa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Pessoa")
@Entity(name = "Pessoa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {
    
    public Pessoa(RequestCadastrarPessoa dadosPessoa) {
        this.nome = dadosPessoa.nome();
        this.cpf = dadosPessoa.cpf();
        this.email = dadosPessoa.email();
        this.telefone = dadosPessoa.telefone();
        this.dataNascimento = dadosPessoa.dataNascimento();
        this.dadosGerais = dadosPessoa.dadosGerais();
        this.observacao = dadosPessoa.observacao();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UsuarioId")
    private Long id;
    @Column(name = "PessoaNome")
    private String nome;
    @Column(name = "PessoaCPF")
    private String cpf;
    @Column(name = "PessoaEmail")
    private String email;
    @Column(name = "PessoaTelefone")
    private String telefone;
    @Column(name = "PessoaDadosGerais")
    private String dadosGerais;
    @Column(name = "PessoaDataNascimento")
    private LocalDate dataNascimento;
    @Column(name = "PessoaObservacao")
    private String observacao;

    public void atualizarInformacoes(@Valid RequestEditarPessoa dadosPessoa) {
        if(dadosPessoa.nome() != null){
            this.nome = dadosPessoa.nome();
        }
        if(dadosPessoa.email() != null){
            this.email = dadosPessoa.email();
        }
        if(dadosPessoa.telefone() != null){
            this.telefone = dadosPessoa.telefone();
        }
        if(dadosPessoa.dadosGerais() != null){
            this.dadosGerais = dadosPessoa.dadosGerais();
        }
        if(dadosPessoa.observacao() != null){
            this.observacao = dadosPessoa.observacao();
        }
    }

}
