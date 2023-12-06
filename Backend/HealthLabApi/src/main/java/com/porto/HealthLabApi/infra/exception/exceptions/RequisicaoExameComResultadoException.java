package com.porto.HealthLabApi.infra.exception.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RequisicaoExameComResultadoException extends RuntimeException {

    public RequisicaoExameComResultadoException(String msg){
        super(msg);
    }

}
