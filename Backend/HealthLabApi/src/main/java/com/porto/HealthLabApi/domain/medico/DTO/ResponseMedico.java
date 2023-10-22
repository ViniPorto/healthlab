package com.porto.HealthLabApi.domain.medico.DTO;

import com.porto.HealthLabApi.domain.medico.Medico;
import com.porto.HealthLabApi.domain.medico.MedicoUF;

public record ResponseMedico(
    Long id,
    String medicoEmail,
    String medicoTelefone,
    String medicoCRM,
    MedicoUF medicoUF,
    String medicoNome
) {
    public ResponseMedico(Medico medico){
        this(medico.getId(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getUf(), medico.getNome());
    }
}
