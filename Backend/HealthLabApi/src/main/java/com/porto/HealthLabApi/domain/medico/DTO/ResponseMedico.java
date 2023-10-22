package com.porto.HealthLabApi.domain.medico.DTO;

import com.porto.HealthLabApi.domain.medico.Medico;
import com.porto.HealthLabApi.domain.medico.MedicoUF;

public record ResponseMedico(
    Long MedicoId,
    String medicoNome,
    String medicoCRM,
    MedicoUF medicoUF,
    String medicoEmail,
    String medicoTelefone
) {
    public ResponseMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getUf(), medico.getEmail(), medico.getTelefone());
    }
}
