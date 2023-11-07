package com.porto.HealthLabApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.bioquimico.Bioquimico;
import com.porto.HealthLabApi.domain.bioquimico.DTO.RequestCadastrarBioquimico;
import com.porto.HealthLabApi.domain.bioquimico.DTO.RequestEditarBioquimico;
import com.porto.HealthLabApi.repositories.BioquimicoRepository;
import com.porto.HealthLabApi.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class BioquimicoService {
    
    @Autowired
    private BioquimicoRepository bioquimicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Bioquimico> listarBioquimicos(Pageable paginacao, String nome) {
        return bioquimicoRepository.findAll(paginacao, nome);
    }

    public Bioquimico detalharBioquimico(Long id) {
        return bioquimicoRepository.findById(id).get();
    }

    @Transactional
    public Bioquimico cadastrarBioquimico(RequestCadastrarBioquimico dadosBioquimico) {
        var usuario = usuarioRepository.findById(dadosBioquimico.usuarioId()).get();
        var bioquimico = new Bioquimico(dadosBioquimico, usuario);

        return bioquimicoRepository.save(bioquimico);
    }

    @Transactional
    public Bioquimico editarBioquimico(RequestEditarBioquimico dadosBioquimico) {
        var bioquimico = bioquimicoRepository.findById(dadosBioquimico.id()).get();
        bioquimico.atualizarInformacoes(dadosBioquimico);

        return bioquimicoRepository.save(bioquimico);
    }

    @Transactional
    public void deletarBioquimico(Long id) {
        var bioquimico = bioquimicoRepository.findById(id).get();

        bioquimicoRepository.delete(bioquimico);
    }

}
