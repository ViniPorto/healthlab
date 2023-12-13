package com.porto.HealthLabApi.domain.agendamentoColeta.DTO;

import java.time.LocalDateTime;

import com.porto.HealthLabApi.domain.agendamentoColeta.AgendamentoColeta;
import com.porto.HealthLabApi.domain.pessoa.DTO.ResponsePessoa;
import com.porto.HealthLabApi.domain.usuario.DTO.ResponseUsuario;

public record ResponseAgendamentoColeta(
    Long agendamentoColetaId,
    LocalDateTime agendamentoDataHoraColeta,
    ResponsePessoa agendamentoPessoa,
    Long agendamentoRequisicaoId,
    ResponseUsuario agendamentoUsuario
) {
    public ResponseAgendamentoColeta(AgendamentoColeta agendamentoColeta){
        this(agendamentoColeta.getId(), agendamentoColeta.getDataHoraColeta(), new ResponsePessoa(agendamentoColeta.getPessoa()), agendamentoColeta.getRequisicao().getId(), new ResponseUsuario(agendamentoColeta.getUsuario()));
    }
}
