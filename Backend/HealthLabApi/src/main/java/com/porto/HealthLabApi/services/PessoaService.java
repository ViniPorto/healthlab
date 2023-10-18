package com.porto.HealthLabApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.pessoa.Pessoa;
import com.porto.HealthLabApi.domain.pessoa.DTO.RequestCadastrarPessoa;
import com.porto.HealthLabApi.domain.pessoa.DTO.RequestEditarPessoa;
import com.porto.HealthLabApi.repositories.PessoaRepository;

import jakarta.validation.Valid;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    // public Page<Pessoa> listarPessoas(Pageable paginacao) {
    //     return pessoaRepository.findAll(paginacao);
    // }

    public Pessoa cadastrarPessoa(@Valid RequestCadastrarPessoa dadosPessoa) {
        var pessoa = new Pessoa(dadosPessoa);
        
        return pessoaRepository.save(pessoa);
    }

    public Pessoa detalharPessoa(Long id) {
        return pessoaRepository.getReferenceById(id);
    }

    public Pessoa editarPessoa(@Valid RequestEditarPessoa dadosPessoa) {
        var pessoa = pessoaRepository.getReferenceById(dadosPessoa.id());
        pessoa.atualizarInformacoes(dadosPessoa);

        return pessoaRepository.save(pessoa);
    }

    public void deletarPessoa(Long id) {
        var pessoa = pessoaRepository.getReferenceById(id);
        System.out.println(pessoa.getNome()); //verificar por que o Spring não lança a exceção ao deletar um user inexistente, porém lança ao printar isto
        pessoaRepository.delete(pessoa);
    }
}
