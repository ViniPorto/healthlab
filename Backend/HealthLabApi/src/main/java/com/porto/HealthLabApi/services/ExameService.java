package com.porto.HealthLabApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.exame.Exame;
import com.porto.HealthLabApi.domain.exame.DTO.RequestCadastrarExame;
import com.porto.HealthLabApi.domain.exame.DTO.RequestEditarExame;
import com.porto.HealthLabApi.domain.layout.Layout;
import com.porto.HealthLabApi.domain.material.Material;
import com.porto.HealthLabApi.domain.metodo.Metodo;
import com.porto.HealthLabApi.domain.setor.Setor;
import com.porto.HealthLabApi.infra.exception.exceptions.SiglaJaCadastradaException;
import com.porto.HealthLabApi.repositories.ExameRepository;
import com.porto.HealthLabApi.repositories.LayoutRepository;
import com.porto.HealthLabApi.repositories.MaterialRepository;
import com.porto.HealthLabApi.repositories.MetodoRepository;
import com.porto.HealthLabApi.repositories.SetorRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ExameService {
    
    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MetodoRepository metodoRepository;

    @Autowired
    private LayoutRepository layoutRepository;

    
    public List<Exame> listarExames(Pageable paginacao, String titulo) {
        return exameRepository.findAll(paginacao, titulo);
    }

    public Exame detalharPorId(Long id) {
        return exameRepository.findById(id).get();
    }

    public Exame detalharPorSigla(String idOuSigle) {
        var exame = exameRepository.findBySigla(idOuSigle);
        if(exame == null){
            throw new EntityNotFoundException();
        }
        return exame;
    }

    @Transactional
    public Exame cadastrarExame(RequestCadastrarExame dadosExame) {
        if(existeExameCadastradoComSiglaInformada(dadosExame.sigla())){
            throw new SiglaJaCadastradaException();
        }

        var setor = setorRepository.findById(dadosExame.setorId()).get();
        var metodo = metodoRepository.findById(dadosExame.metodoId()).get();
        var material = materialRepository.findById(dadosExame.materialId()).get();
        var layout = layoutRepository.save(new Layout());
        var exame = new Exame(dadosExame, layout, setor, metodo, material);

        return exameRepository.save(exame);
    }

    @Transactional
    public Exame editarExame(RequestEditarExame dadosExame) {
        
        var exame = exameRepository.findById(dadosExame.exameId()).get();

        if(!dadosExame.sigla().toUpperCase().equals(dadosExame.sigla()) && existeExameCadastradoComSiglaInformada(dadosExame.sigla())){
            throw new SiglaJaCadastradaException();
        }

        Setor setor = dadosExame.setorId()!= null ? setorRepository.findById(dadosExame.setorId()).get() : null; 
        Metodo metodo = dadosExame.metodoId() != null ? metodoRepository.findById(dadosExame.metodoId()).get() : null;
        Material material = dadosExame.materialId() != null ? materialRepository.findById(dadosExame.materialId()).get() : null;

        exame.atualizarInformacoes(dadosExame, setor, metodo, material);

        return exameRepository.save(exame);
    }

    @Transactional
    public void deletarExame(Long id) {
        var exame = exameRepository.findById(id).get();

        exameRepository.delete(exame);
    }

    private boolean existeExameCadastradoComSiglaInformada(String sigla){
        return exameRepository.existsBySigla(sigla);
    }

    
    

}
