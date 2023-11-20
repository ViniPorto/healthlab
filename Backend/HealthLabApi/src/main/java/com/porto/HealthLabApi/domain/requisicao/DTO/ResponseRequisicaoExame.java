package com.porto.HealthLabApi.domain.requisicao.DTO;

import java.time.LocalDate;

import com.porto.HealthLabApi.domain.bioquimico.DTO.ResponseBioquimico;
import com.porto.HealthLabApi.domain.exame.DTO.ResponseExame;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayout;
import com.porto.HealthLabApi.domain.motivoRecoleta.DTO.ResponseMotivoRecoleta;
import com.porto.HealthLabApi.domain.status.Status;

public record ResponseRequisicaoExame(
    Long requisicaoExameId,
    ResponseExame requisicaoExameExame,
    ResponseLayout requisicaoExameLayout,
    LocalDate requisicaoExameDataHoraColeta,
    LocalDate requisicaoExameDataHoraInclusao,
    LocalDate requisicaoExameDataHoraTriagem,
    Boolean requisicaoExameImpresso,
    ResponseMotivoRecoleta requisicaoExameMotivoRecoleta,
    ResponseBioquimico requisicaoExameBioquimico,
    String requisicaoExameBioquimicoAssinatura,
    Status requisicaoExameStatusCodigo,
    ResponseRequisicaoExameItensResultado requisicaoExameItensResultado
) {
    
}
