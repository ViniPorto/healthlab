package com.porto.HealthLabApi.domain.exame.DTO;

import java.math.BigDecimal;

import com.porto.HealthLabApi.domain.exame.Exame;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayout;
import com.porto.HealthLabApi.domain.material.DTO.ResponseMaterial;
import com.porto.HealthLabApi.domain.metodo.DTO.ResponseMetodo;
import com.porto.HealthLabApi.domain.setor.DTO.ResponseSetor;

public record ResponseExame(
    Long exameId,
    String exameTitulo,
    String exameSigla,
    String exameDescricao,
    boolean examePrincipal,
    ResponseSetor exameSetor,
    ResponseMaterial exameMaterial,
    ResponseMetodo exameMetodo,
    BigDecimal examePreco,
    Integer exameTempoExecucaoNormal,
    Integer exameTempoExecucaoUrgente,
    ResponseLayout exameLayout
) {

    public ResponseExame(Exame exame, ResponseSetor responseSetor, ResponseMaterial responseMaterial, ResponseMetodo responseMetodo, ResponseLayout responseLayout) {
        this(exame.getId(), exame.getTitulo(), exame.getSigla(), exame.getDescricao(), exame.isPrincipal(), responseSetor, responseMaterial, responseMetodo, exame.getPreco(), exame.getTempoExecucaoNormal(), exame.getTempoExecucaoUrgente(), responseLayout);
    }

}
