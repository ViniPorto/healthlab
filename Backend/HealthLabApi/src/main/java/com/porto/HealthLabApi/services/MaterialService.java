package com.porto.HealthLabApi.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.historico.Historico;
import com.porto.HealthLabApi.domain.material.Material;
import com.porto.HealthLabApi.domain.material.DTO.RequestCadastrarMaterial;
import com.porto.HealthLabApi.domain.material.DTO.RequestEditarMaterial;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.repositories.HistoricoRepository;
import com.porto.HealthLabApi.repositories.MaterialRepository;

import jakarta.transaction.Transactional;

@Service
public class MaterialService {
    
    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    public Page<Material> listarMateriais(Pageable paginacao, String nome) {
        return materialRepository.findAll(paginacao, nome);
    }

    public Material detalharMaterial(Long id) {
        return materialRepository.findById(id).get();
    }

    @Transactional
    public Material cadastrarMaterial(RequestCadastrarMaterial dadosMaterial, Usuario usuario) {
        var material = new Material(dadosMaterial);

        materialRepository.save(material);

        historicoRepository.save(new Historico(material.getId(), "MATERIAL", usuario, "CADASTRO", LocalDateTime.now(), gerarDados(material)));

        return material;
    }

    @Transactional
    public Material editarMaterial(RequestEditarMaterial dadosMaterial, Usuario usuario) {
        var material = materialRepository.findById(dadosMaterial.id()).get();
        material.atualizarInformacoes(dadosMaterial);

        materialRepository.save(material);

        historicoRepository.save(new Historico(material.getId(), "MATERIAL", usuario, "EDIÇÃO", LocalDateTime.now(), gerarDados(material)));

        return material;
    }

    @Transactional
    public void deletarMaterial(Long id) {
        var material = materialRepository.findById(id).get();

        materialRepository.delete(material);
    }

    private String gerarDados(Material material){
        return "NOME: " + material.getNome() +
        "\nDESCRICAO: " + material.getDescricao();
    }

}
