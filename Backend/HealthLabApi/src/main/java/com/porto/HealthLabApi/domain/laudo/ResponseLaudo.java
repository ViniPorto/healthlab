package com.porto.HealthLabApi.domain.laudo;

import java.util.List;

import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayoutCampos;
import com.porto.HealthLabApi.domain.requisicao.DTO.ResponseRequisicaoExameItensResultado;

public record ResponseLaudo(
    List<ResponseLayoutCampos> layoutCampos,
    List<ResponseRequisicaoExameItensResultado> itensResultado
) {
    
}
