package com.porto.HealthLabApi.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.porto.HealthLabApi.infra.exceptions.UsuarioNaoAdministradorException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {

    @Autowired
    private ResponseHandler responseHandler;
 
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> tratarErro404(){
        return responseHandler.generateResponse("Não encontrado", false, HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return responseHandler.generateResponse("Erro de validação dos campos", false, HttpStatus.BAD_REQUEST, erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> tratarErroIntegridadeJSON(HttpMessageNotReadableException ex){
        return responseHandler.generateResponse("Erro de na validação dos campos, integridade do JSON comprometida", false, HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Object> tratarErro500(){
        return responseHandler.generateResponse("Erro interno do servidor", false, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    @ExceptionHandler(UsuarioNaoAdministradorException.class)
    public ResponseEntity<Object> tratarErroUsuarioNaoAdministrador(){
        return responseHandler.generateResponse("Usuário sem permissão para realizar ação", false, HttpStatus.FORBIDDEN, null);
    }

    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
