package com.porto.HealthLabApi.domain.orcamento.DTO;

import java.util.List;

import com.porto.HealthLabApi.domain.exame.DTO.ResponseExame;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayout;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayoutCampos;
import com.porto.HealthLabApi.domain.material.DTO.ResponseMaterial;
import com.porto.HealthLabApi.domain.metodo.DTO.ResponseMetodo;
import com.porto.HealthLabApi.domain.orcamento.OrcamentoExame;
import com.porto.HealthLabApi.domain.setor.DTO.ResponseSetor;

public record ResponseOrcamentoExame(
    Long orcamentoExameId,
    ResponseExame orcamentoExame
) {

    public ResponseOrcamentoExame(OrcamentoExame orcamentoExame, List<ResponseLayoutCampos> responseLayoutCampos) {
        this(orcamentoExame.getId(), new ResponseExame(orcamentoExame.getExame(), new ResponseSetor(orcamentoExame.getExame().getSetor()), new ResponseMaterial(orcamentoExame.getExame().getMaterial()), new ResponseMetodo(orcamentoExame.getExame().getMetodo()), new ResponseLayout(orcamentoExame.getExame().getLayout(), responseLayoutCampos)));
    }
    
}
