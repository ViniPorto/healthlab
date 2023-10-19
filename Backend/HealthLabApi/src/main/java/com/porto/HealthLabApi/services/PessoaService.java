package com.porto.HealthLabApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.pessoa.Pessoa;
import com.porto.HealthLabApi.domain.pessoa.DTO.RequestCadastrarPessoa;
import com.porto.HealthLabApi.domain.pessoa.DTO.RequestEditarPessoa;
import com.porto.HealthLabApi.infra.exception.exceptions.CPFJaCadastradoException;
import com.porto.HealthLabApi.repositories.PessoaRepository;

import jakarta.transaction.Transactional;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;;

    public Page<Pessoa> listarPessoas(Pageable paginacao) {
        return pessoaRepository.findAll(paginacao);
    }

    @Transactional
    public Pessoa cadastrarPessoa(RequestCadastrarPessoa dadosPessoa) {
        if(pessoaRepository.existsByCPF(dadosPessoa.CPF())){
            throw new CPFJaCadastradoException();
        }

        var pessoa = new Pessoa(dadosPessoa);
        
        return pessoaRepository.save(pessoa);
    }

    public Pessoa detalharPessoa(Long id) {
        return pessoaRepository.findById(id).get();
    }

    @Transactional
    public Pessoa editarPessoa(RequestEditarPessoa dadosPessoa) {
        var pessoa = pessoaRepository.findById(dadosPessoa.id()).get();
        pessoa.atualizarInformacoes(dadosPessoa);

        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void deletarPessoa(Long id) {
        var pessoa = pessoaRepository.findById(id).get();
        
        pessoaRepository.delete(pessoa);
    }

    
}
