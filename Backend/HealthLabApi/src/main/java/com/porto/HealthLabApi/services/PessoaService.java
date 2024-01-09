package com.porto.HealthLabApi.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.historico.Historico;
import com.porto.HealthLabApi.domain.pessoa.Pessoa;
import com.porto.HealthLabApi.domain.pessoa.DTO.RequestCadastrarPessoa;
import com.porto.HealthLabApi.domain.pessoa.DTO.RequestEditarPessoa;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.infra.exception.exceptions.CPFJaCadastradoException;
import com.porto.HealthLabApi.repositories.HistoricoRepository;
import com.porto.HealthLabApi.repositories.PessoaRepository;

import jakarta.transaction.Transactional;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    public Page<Pessoa> listarPessoas(Pageable paginacao, String nome, String cpf) {
        return pessoaRepository.findAll(paginacao, nome, cpf);
    }

    @Transactional
    public Pessoa cadastrarPessoa(RequestCadastrarPessoa dadosPessoa, Usuario usuario) {
        if(pessoaRepository.existsByCpf(dadosPessoa.cpf())){
            throw new CPFJaCadastradoException();
        }

        var pessoa = new Pessoa(dadosPessoa);
        
        pessoaRepository.save(pessoa);

        historicoRepository.save(new Historico(pessoa.getId(), "PESSOA", usuario, "CADASTRO", LocalDateTime.now(), gerarDados(pessoa)));

        return pessoa;
    }

    public Pessoa detalharPessoa(Long id) {
        return pessoaRepository.findById(id).get();
    }

    @Transactional
    public Pessoa editarPessoa(RequestEditarPessoa dadosPessoa, Usuario usuario) {
        var pessoa = pessoaRepository.findById(dadosPessoa.id()).get();
        pessoa.atualizarInformacoes(dadosPessoa);

        pessoaRepository.save(pessoa);

        historicoRepository.save(new Historico(pessoa.getId(), "PESSOA", usuario, "EDIÇÃO", LocalDateTime.now(), gerarDados(pessoa)));

        return pessoa;
    }

    @Transactional
    public void deletarPessoa(Long id) {
        var pessoa = pessoaRepository.findById(id).get();
        
        pessoaRepository.delete(pessoa);
    }

    private String gerarDados(Pessoa pessoa){
        return "NOME: " + pessoa.getNome() +
        "\nCPF: " + pessoa.getCpf() +
        "\nEMAIL: " + pessoa.getEmail() +
        "\nTELEFONE: " + pessoa.getTelefone() +
        "\nDATA DE NASCIMENTO: " + pessoa.getDataNascimento() +
        "\nDADOS GERAIS: " + pessoa.getDadosGerais() +
        "\nOBSERVAÇÃO: " + pessoa.getObservacao(); 
    }
    
}
