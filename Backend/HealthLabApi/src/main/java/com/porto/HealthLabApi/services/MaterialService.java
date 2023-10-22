package com.porto.HealthLabApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.material.Material;
import com.porto.HealthLabApi.domain.material.DTO.RequestCadastrarMaterial;
import com.porto.HealthLabApi.domain.material.DTO.RequestEditarMaterial;
import com.porto.HealthLabApi.repositories.MaterialRepository;

import jakarta.transaction.Transactional;

@Service
public class MaterialService {
    
    @Autowired
    private MaterialRepository materialRepository;

    public Page<Material> listarMateriais(Pageable paginacao, String nome) {
        return materialRepository.findAll(paginacao, nome);
    }

    public Material detalharMaterial(Long id) {
        return materialRepository.findById(id).get();
    }

    @Transactional
    public Material cadastrarMaterial(RequestCadastrarMaterial dadosMaterial) {
        var material = new Material(dadosMaterial);

        return materialRepository.save(material);
    }

    @Transactional
    public Material editarMaterial(RequestEditarMaterial dadosMaterial) {
        var material = materialRepository.findById(dadosMaterial.id()).get();
        material.atualizarInformacoes(dadosMaterial);

        return materialRepository.save(material);
    }

    @Transactional
    public void deletarMaterial(Long id) {
        var material = materialRepository.findById(id).get();

        materialRepository.delete(material);
    }

    //TODO

}
