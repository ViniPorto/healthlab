package com.porto.HealthLabApi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.laudo.RequestGerarLaudo;
import com.porto.HealthLabApi.domain.laudo.ResponseLaudo;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayoutCampos;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExame;
import com.porto.HealthLabApi.domain.requisicao.DTO.ResponseRequisicaoExameItensResultado;
import com.porto.HealthLabApi.repositories.LayoutCamposRepository;
import com.porto.HealthLabApi.repositories.RequisicaoRepository;

@Service
public class LaudoService {

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    @Autowired
    private LayoutCamposRepository layoutCamposRepository;

    public List<ResponseLaudo> gerarLaudos(RequestGerarLaudo dadosLaudo) {
        List<ResponseLaudo> responseLaudo = new ArrayList<>();
        for(Long requisicaoId : dadosLaudo.requisicoesId()){
            var requisicao = requisicaoRepository.findById(requisicaoId).get();
            for(RequisicaoExame requisicaoExame : requisicao.getRequisicaoExames()){
                if(requisicaoExame.getStatus().getCodigo().equals("RL")){
                    var requisicaoExameItensResultado = requisicaoExame.getItensResultado().stream().map(ResponseRequisicaoExameItensResultado::new).toList();
                    var layoutCampos = layoutCamposRepository.findByLayout(requisicaoExame.getExame().getLayout()).stream().map(ResponseLayoutCampos::new).toList();

                    responseLaudo.add(new ResponseLaudo(layoutCampos, requisicaoExameItensResultado));
                }
            }
        }
        return responseLaudo;
    }
    

}
