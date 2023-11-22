package com.porto.HealthLabApi.domain.requisicao.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.porto.HealthLabApi.domain.medico.DTO.ResponseMedico;
import com.porto.HealthLabApi.domain.pessoa.DTO.ResponsePessoa;
import com.porto.HealthLabApi.domain.requisicao.Requisicao;
import com.porto.HealthLabApi.domain.usuario.DTO.ResponseUsuario;

public record ResponseRequisicao(
    Long requisicaoId,
    ResponsePessoa requisicaoPessoa,
    ResponseMedico requisicaoMedicoId,
    LocalDateTime requisicaoData,
    boolean requisicaoUrgente,
    ResponseUsuario requisicaoUsuario,
    boolean requisicaoPaga,
    BigDecimal requisicaoPrecoTotal,
    List<ResponseRequisicaoExame> requisicaoExames
) {

    public ResponseRequisicao(Requisicao requisicao, List<ResponseRequisicaoExame> requisicaoExames) {
        this(requisicao.getId(), 
            new ResponsePessoa(requisicao.getPessoa()), 
            requisicao.getMedico() != null ? new ResponseMedico(requisicao.getMedico()) : null, 
            requisicao.getData(), 
            requisicao.isUrgente(), 
            new ResponseUsuario(requisicao.getUsuario()), 
            requisicao.isPaga(), 
            requisicao.getPretoTotal(), 
            requisicaoExames);
    }
    
}
