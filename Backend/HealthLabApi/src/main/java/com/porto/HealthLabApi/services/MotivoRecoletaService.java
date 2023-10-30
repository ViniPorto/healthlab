package com.porto.HealthLabApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.motivoRecoleta.MotivoRecoleta;
import com.porto.HealthLabApi.domain.motivoRecoleta.DTO.RequestCadastrarMotivoRecoleta;
import com.porto.HealthLabApi.domain.motivoRecoleta.DTO.RequestEditarMotivoRecoleta;
import com.porto.HealthLabApi.repositories.MotivoRecoletaRepository;

import jakarta.transaction.Transactional;

@Service
public class MotivoRecoletaService {
    
    @Autowired
    private MotivoRecoletaRepository motivoRecoletaRepository;

    public Page<MotivoRecoleta> listarMotivosRecoleta(Pageable paginacao) {
        return motivoRecoletaRepository.findAll(paginacao);
    }

    public MotivoRecoleta detalharMotivoRecoleta(Long id) {
        return motivoRecoletaRepository.findById(id).get();
    }

    @Transactional
    public MotivoRecoleta cadastrarMotivoRecoleta(RequestCadastrarMotivoRecoleta dadosMotivoRecoleta) {
        var motivoRecoleta = new MotivoRecoleta(dadosMotivoRecoleta);

        return motivoRecoletaRepository.save(motivoRecoleta);
    }

    @Transactional
    public MotivoRecoleta editarMotivoRecoleta(RequestEditarMotivoRecoleta dadosMotivoRecoleta) {
        var motivoRecoleta = motivoRecoletaRepository.findById(dadosMotivoRecoleta.id()).get();

        motivoRecoleta.atualizarInformacoes(dadosMotivoRecoleta);

        return motivoRecoletaRepository.save(motivoRecoleta);
    }

    @Transactional
    public void deletarMotivoRecoleta(Long id) {
        var motivoRecoleta = motivoRecoletaRepository.findById(id).get();

        motivoRecoletaRepository.delete(motivoRecoleta);
    }



}
