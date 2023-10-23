package com.porto.HealthLabApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.exame.Exame;
import com.porto.HealthLabApi.repositories.ExameRepository;

@Service
public class ExameService {
    
    @Autowired
    private ExameRepository exameRepository;

    public List<Exame> listarExames(Pageable paginacao, String titulo) {
        return exameRepository.findAll(paginacao, titulo);
    }



}
