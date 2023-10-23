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
    ResponseSetor setor,
    ResponseMaterial material,
    ResponseMetodo metodo,
    BigDecimal examePreco,
    Integer tempoExecucaoNormal,
    Integer tempoExecucaoUrgente,
    ResponseLayout layout
) {

    public ResponseExame(Exame exame, ResponseSetor responseSetor, ResponseMaterial responseMaterial, ResponseMetodo responseMetodo, ResponseLayout responseLayout) {
        this(exame.getId(), exame.getTitulo(), exame.getSigla(), exame.getDescricao(), exame.isPrincipal(), responseSetor, responseMaterial, responseMetodo, exame.getPreco(), exame.getTempoExecucao(), exame.getTempoExecucaoUrgente(), responseLayout);
    }

}
