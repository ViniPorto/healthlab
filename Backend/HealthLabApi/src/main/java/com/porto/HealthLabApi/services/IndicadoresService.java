package com.porto.HealthLabApi.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.indicadores.DTO.ResponseIndicadoresExames;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExame;
import com.porto.HealthLabApi.repositories.RequisicaoExameRepository;

@Service
public class IndicadoresService {

    @Autowired
    private RequisicaoExameRepository requisicaoExameRepository;

    public List<ResponseIndicadoresExames> consultarIndicadores() {
        var requisicoesExames = requisicaoExameRepository.listarExamesNaoLiberados();
        List<ResponseIndicadoresExames> listaIndicadores = new ArrayList<>();

        for(RequisicaoExame requisicaoExame : requisicoesExames){
            LocalDateTime horaPrevisao = requisicaoExame.getDataHoraColeta()
                                    .plusMinutes(requisicaoExame.getRequisicao().isUrgente() ? requisicaoExame.getExame().getTempoExecucaoUrgente() : requisicaoExame.getExame().getTempoExecucaoNormal());
            var delta = ChronoUnit.MINUTES.between(LocalDateTime.now(), horaPrevisao);

            String status = null;

            if(delta < 0){
                status = "A"; //atrasado
            }else if(delta > 0){
                status = "D"; //dentro do tempo
            }else{
                status = "N"; //neutro
            }
            listaIndicadores.add(new ResponseIndicadoresExames(requisicaoExame.getRequisicao().getId(), 
                                                                   requisicaoExame.getRequisicao().getPessoa().getNome(), 
                                                                   requisicaoExame.getExame().getTitulo(), 
                                                                   horaPrevisao, 
                                                                   Math.abs(delta), 
                                                                   status));
        }

        return listaIndicadores;
    }
    


}
