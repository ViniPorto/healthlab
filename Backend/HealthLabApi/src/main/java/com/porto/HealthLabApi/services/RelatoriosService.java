package com.porto.HealthLabApi.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.relatorios.examesMaisRealizados.DTO.ResponseRelatorioExamesMaisRealizados;
import com.porto.HealthLabApi.domain.relatorios.examesRealizadosPorPeriodo.DTO.RequestRelatorioExamesRealizadosPorPeriodo;
import com.porto.HealthLabApi.domain.relatorios.examesRealizadosPorPeriodo.DTO.ResponseRelatorioExamesRealizadosPorPeriodo;
import com.porto.HealthLabApi.domain.relatorios.examesRealizadosPorPeriodo.DTO.ResponseRelatorioExamesRealizadosPorPeriodoExames;
import com.porto.HealthLabApi.repositories.RequisicaoExameRepository;

import jakarta.validation.Valid;

@Service
public class RelatoriosService {
    
    @Autowired
    private RequisicaoExameRepository requisicaoExameRepository;

    public List<ResponseRelatorioExamesRealizadosPorPeriodo> gerarRelatorioExamesPorPeriodo(@Valid RequestRelatorioExamesRealizadosPorPeriodo dadosRelatorio) {
        List<ResponseRelatorioExamesRealizadosPorPeriodo> relatorio = new ArrayList<>();
        for(LocalDate data = dadosRelatorio.dataInicial(); data.isBefore(dadosRelatorio.dataFinal().plusDays(1)); data = data.plusDays(1)){
            List<ResponseRelatorioExamesRealizadosPorPeriodoExames> responseRelatorioExames = requisicaoExameRepository.listarQuantidadeEExamesRealizadosPorPeriodo(data);
            var numeroDeExamesNaData = requisicaoExameRepository.listarQuantidadeDeExamesRealizadosNaData(data);
            if(numeroDeExamesNaData > 0){
                relatorio.add(new ResponseRelatorioExamesRealizadosPorPeriodo(data, numeroDeExamesNaData, responseRelatorioExames));
            }
        }

        return relatorio;
    }

    public List<ResponseRelatorioExamesMaisRealizados> gerarRelatorio10ExamesMaisRealizados() {
        return requisicaoExameRepository.listarExamesMaisRealizados();
    }

    
}
