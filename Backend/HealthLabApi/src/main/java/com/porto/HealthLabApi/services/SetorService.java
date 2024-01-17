package com.porto.HealthLabApi.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.historico.Historico;
import com.porto.HealthLabApi.domain.setor.Setor;
import com.porto.HealthLabApi.domain.setor.DTO.RequestCadastrarSetor;
import com.porto.HealthLabApi.domain.setor.DTO.RequestEditarSetor;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.repositories.HistoricoRepository;
import com.porto.HealthLabApi.repositories.SetorRepository;

import jakarta.transaction.Transactional;

@Service
public class SetorService {
    
    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    public Page<Setor> listarSetores(Pageable paginacao, String nome) {
        return setorRepository.findAll(paginacao, nome);
    }

    public Setor detalharSetor(Long id) {
        return setorRepository.findById(id).get();
    }

    @Transactional
    public Setor cadastrarSetor(RequestCadastrarSetor dadosSetor, Usuario usuario) {
        var setor = new Setor(dadosSetor);

        setorRepository.save(setor);

        historicoRepository.save(new Historico(setor.getId(), "SETOR", usuario, "CADASTRO", LocalDateTime.now(), gerarDados(setor)));

        return setor;
    }

    @Transactional
    public Setor editarSetor(RequestEditarSetor dadosSetor, Usuario usuario) {
        var setor = setorRepository.findById(dadosSetor.id()).get();
        setor.atualizarInformacoes(dadosSetor);

        setorRepository.save(setor);

        historicoRepository.save(new Historico(setor.getId(), "SETOR", usuario, "EDIÇÃO", LocalDateTime.now(), gerarDados(setor)));

        return setor;
    }

    @Transactional
    public void deletarSetor(Long id) {
        var setor = setorRepository.findById(id).get();

        setorRepository.delete(setor);
    }

    private String gerarDados(Setor setor){
        return "NOME: " + setor.getNome() +
        "\nDESCRICAO: " + setor.getDescricao();
    }

}
