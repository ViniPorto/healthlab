package com.porto.HealthLabApi.domain.bioquimico.DTO;

import com.porto.HealthLabApi.domain.bioquimico.Bioquimico;
import com.porto.HealthLabApi.domain.usuario.DTO.ResponseUsuario;

public record ResponseBioquimico(
    Long bioquimicoId,
    String bioquimicoNome,
    ResponseUsuario bioquimicoUsuario,
    String bioquimicoAssinatura
) {
    public ResponseBioquimico(Bioquimico bioquimico, ResponseUsuario responseUsuario){
        this(bioquimico.getId(), bioquimico.getNome(), responseUsuario, bioquimico.getAssinatura());
    }
}
