package com.porto.HealthLabApi.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.historico.Historico;
import com.porto.HealthLabApi.domain.motivoRecoleta.MotivoRecoleta;
import com.porto.HealthLabApi.domain.motivoRecoleta.DTO.RequestCadastrarMotivoRecoleta;
import com.porto.HealthLabApi.domain.motivoRecoleta.DTO.RequestEditarMotivoRecoleta;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.repositories.HistoricoRepository;
import com.porto.HealthLabApi.repositories.MotivoRecoletaRepository;

import jakarta.transaction.Transactional;

@Service
public class MotivoRecoletaService {
    
    @Autowired
    private MotivoRecoletaRepository motivoRecoletaRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    public Page<MotivoRecoleta> listarMotivosRecoleta(Pageable paginacao) {
        return motivoRecoletaRepository.findAll(paginacao);
    }

    public MotivoRecoleta detalharMotivoRecoleta(Long id) {
        return motivoRecoletaRepository.findById(id).get();
    }

    @Transactional
    public MotivoRecoleta cadastrarMotivoRecoleta(RequestCadastrarMotivoRecoleta dadosMotivoRecoleta, Usuario usuario) {
        var motivoRecoleta = new MotivoRecoleta(dadosMotivoRecoleta);

        motivoRecoletaRepository.save(motivoRecoleta);

        historicoRepository.save(new Historico(motivoRecoleta.getId(), "MOTIVO RECOLETA", usuario, "CADASTRO", LocalDateTime.now(), gerarDados(motivoRecoleta)));

        return motivoRecoleta;
    }

    @Transactional
    public MotivoRecoleta editarMotivoRecoleta(RequestEditarMotivoRecoleta dadosMotivoRecoleta, Usuario usuario) {
        var motivoRecoleta = motivoRecoletaRepository.findById(dadosMotivoRecoleta.id()).get();

        motivoRecoleta.atualizarInformacoes(dadosMotivoRecoleta);

        motivoRecoletaRepository.save(motivoRecoleta);

        historicoRepository.save(new Historico(motivoRecoleta.getId(), "MOTIVO RECOLETA", usuario, "EDIÇÃO", LocalDateTime.now(), gerarDados(motivoRecoleta)));

        return motivoRecoleta;
    }

    @Transactional
    public void deletarMotivoRecoleta(Long id) {
        var motivoRecoleta = motivoRecoletaRepository.findById(id).get();

        motivoRecoletaRepository.delete(motivoRecoleta);
    }

    private String gerarDados(MotivoRecoleta motivoRecoleta){
        return "NOME: " + motivoRecoleta.getNome() +
        "\nDESCRIÇÃO: " + motivoRecoleta.getDescricao();
    }

}
