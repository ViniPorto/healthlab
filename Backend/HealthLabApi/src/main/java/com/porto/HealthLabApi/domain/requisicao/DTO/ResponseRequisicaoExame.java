package com.porto.HealthLabApi.domain.requisicao.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.porto.HealthLabApi.domain.bioquimico.DTO.ResponseBioquimico;
import com.porto.HealthLabApi.domain.exame.DTO.ResponseExame;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayout;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayoutCampos;
import com.porto.HealthLabApi.domain.material.DTO.ResponseMaterial;
import com.porto.HealthLabApi.domain.metodo.DTO.ResponseMetodo;
import com.porto.HealthLabApi.domain.motivoRecoleta.DTO.ResponseMotivoRecoleta;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExame;
import com.porto.HealthLabApi.domain.setor.DTO.ResponseSetor;
import com.porto.HealthLabApi.domain.status.Status;
import com.porto.HealthLabApi.domain.usuario.DTO.ResponseUsuario;

public record ResponseRequisicaoExame(
    Long requisicaoExameId,
    ResponseExame requisicaoExameExame,
    ResponseLayout requisicaoExameLayout,
    LocalDateTime requisicaoExameDataHoraColeta,
    LocalDateTime requisicaoExameDataHoraInclusao,
    LocalDateTime requisicaoExameDataHoraTriagem,
    boolean requisicaoExameImpresso,
    ResponseMotivoRecoleta requisicaoExameMotivoRecoleta,
    ResponseBioquimico requisicaoExameBioquimico,
    Status requisicaoExameStatusCodigo,
    List<ResponseRequisicaoExameItensResultado> requisicaoExameItensResultado
) {

    public ResponseRequisicaoExame(RequisicaoExame requisicaoExame, List<ResponseRequisicaoExameItensResultado> responseItensResultado, List<ResponseLayoutCampos> responseLayoutCampos) {
        this(requisicaoExame.getId(), 
            new ResponseExame(requisicaoExame.getExame(), 
                new ResponseSetor(requisicaoExame.getExame().getSetor()), 
                new ResponseMaterial(requisicaoExame.getExame().getMaterial()), 
                new ResponseMetodo(requisicaoExame.getExame().getMetodo()), 
                new ResponseLayout(requisicaoExame.getLayout(), responseLayoutCampos)),
            new ResponseLayout(requisicaoExame.getLayout(), responseLayoutCampos),
            requisicaoExame.getDataHoraColeta(),
            requisicaoExame.getDataHoraInclusao(),
            requisicaoExame.getDataHoraTriagem(),
            requisicaoExame.isExameImpresso(),
            requisicaoExame.getMotivoRecoleta() != null ? new ResponseMotivoRecoleta(requisicaoExame.getMotivoRecoleta()) : null,
            requisicaoExame.getBioquimico() != null ? new ResponseBioquimico(requisicaoExame.getBioquimico(), new ResponseUsuario(requisicaoExame.getBioquimico().getUsuario())) : null,
            requisicaoExame.getStatus(),
            responseItensResultado);
    }
    
}
