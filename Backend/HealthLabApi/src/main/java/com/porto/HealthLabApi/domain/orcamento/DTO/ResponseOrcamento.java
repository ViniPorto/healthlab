package com.porto.HealthLabApi.domain.orcamento.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.porto.HealthLabApi.domain.medico.DTO.ResponseMedico;
import com.porto.HealthLabApi.domain.orcamento.Orcamento;
import com.porto.HealthLabApi.domain.pessoa.DTO.ResponsePessoa;
import com.porto.HealthLabApi.domain.usuario.DTO.ResponseUsuario;

public record ResponseOrcamento(
    Long orcamentoId,
    ResponseUsuario orcamentoUsuario,
    ResponsePessoa orcamentoPessoa,
    ResponseMedico orcamentoMedico,
    LocalDate orcamentoData,
    BigDecimal orcamentoPrecoTotal,
    List<ResponseOrcamentoExame> orcamentoExames
) {

    public ResponseOrcamento(Orcamento orcamento, List<ResponseOrcamentoExame> responseOrcamentoExames) {
        this(orcamento.getId(), 
            new ResponseUsuario(orcamento.getUsuario()), 
            new ResponsePessoa(orcamento.getPessoa()), 
            orcamento.getMedico() != null ? new ResponseMedico(orcamento.getMedico()) : null, 
            orcamento.getData(),
            orcamento.getPrecoTotal(),
            responseOrcamentoExames);
    }
    
}
