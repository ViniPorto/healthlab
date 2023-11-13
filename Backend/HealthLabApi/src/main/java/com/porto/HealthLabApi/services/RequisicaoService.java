package com.porto.HealthLabApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.repositories.RequisicaoExameItensResultadoRepository;
import com.porto.HealthLabApi.repositories.RequisicaoExameRepository;
import com.porto.HealthLabApi.repositories.RequisicaoRepository;

@Service
public class RequisicaoService {
    
    @Autowired
    private RequisicaoRepository requisicaoRepository;

    @Autowired
    private RequisicaoExameRepository requisicaoExameRepository;

    @Autowired
    private RequisicaoExameItensResultadoRepository requisicaoExameItensResultadoRepository;

}
