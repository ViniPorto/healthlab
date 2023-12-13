package com.porto.HealthLabApi.infra.exception.exceptions;

public class AgendamentoJaCadastradoNoHorarioException extends RuntimeException {
    
    public AgendamentoJaCadastradoNoHorarioException(String msg){
        super(msg);
    }
    
}
