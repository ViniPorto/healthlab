package com.porto.HealthLabApi.domain.requisicao.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.porto.HealthLabApi.domain.medico.DTO.ResponseMedico;
import com.porto.HealthLabApi.domain.pessoa.DTO.ResponsePessoa;
import com.porto.HealthLabApi.domain.usuario.DTO.ResponseUsuario;

public record ResponseRequisicao(
    Long requisicaoId,
    ResponsePessoa requisicaoPessoa,
    ResponseMedico requisicaoMedicoId,
    LocalDate requisicaoData,
    Boolean requisicaoUrgente,
    ResponseUsuario requisicaoUsuario,
    Boolean requisicaoPaga,
    BigDecimal requisicaoPrecoTotal,
    List<ResponseRequisicaoExame> requisicaoExames
) {
    
}
