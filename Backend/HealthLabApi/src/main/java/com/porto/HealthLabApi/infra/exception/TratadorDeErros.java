package com.porto.HealthLabApi.infra.exception;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.porto.HealthLabApi.infra.exception.exceptions.CPFJaCadastradoException;
import com.porto.HealthLabApi.infra.exception.exceptions.MedicoJaCadastradoException;
import com.porto.HealthLabApi.infra.exception.exceptions.TokenJWTInvalidoOuExpiradoException;
import com.porto.HealthLabApi.infra.exception.exceptions.TokenJWTNaoInformadoException;
import com.porto.HealthLabApi.infra.exception.exceptions.UsuarioNaoAdministradorException;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class TratadorDeErros implements AccessDeniedHandler{

    @Autowired
    private ResponseHandler responseHandler;
 
    @ExceptionHandler({EntityNotFoundException.class, NoHandlerFoundException.class, NoSuchElementException.class})
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

    @ExceptionHandler({InternalServerError.class, NotReadablePropertyException.class})
    public ResponseEntity<Object> tratarErro500(){
        return responseHandler.generateResponse("Erro interno do servidor", false, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    @ExceptionHandler(UsuarioNaoAdministradorException.class)
    public ResponseEntity<Object> tratarErroUsuarioNaoAdministrador(){
        return responseHandler.generateResponse("Usuário sem permissão para realizar ação", false, HttpStatus.FORBIDDEN, null);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> tratarErroCredenciaisInvalidas(){
        return responseHandler.generateResponse("Credenciais inválidas", false, HttpStatus.UNAUTHORIZED, null);
    }

    @ExceptionHandler(TokenJWTNaoInformadoException.class)
    public ResponseEntity<Object> tratarErroTokenJWTNaoInformado(){
        return responseHandler.generateResponse("Token JWT não informado", false, HttpStatus.UNAUTHORIZED, null);
    }

    @ExceptionHandler(TokenJWTInvalidoOuExpiradoException.class)
    public ResponseEntity<Object> tratarErroTokenJWTInvalidoOuExpirado(){
        return responseHandler.generateResponse("Token JWT inválido ou expirado", false, HttpStatus.FORBIDDEN, null);
    }

    @ExceptionHandler(CPFJaCadastradoException.class)
    public ResponseEntity<Object> tratarErroCPFJaCadastrado(){
        return responseHandler.generateResponse("CPF já cadastrado", false, HttpStatus.FORBIDDEN, null); 
    }
    
    @ExceptionHandler(MedicoJaCadastradoException.class)
    public ResponseEntity<Object> tratarErroCRMJaCadastrado(){
        return responseHandler.generateResponse("Médico já cadastrado com o CRM e UF informados", false, HttpStatus.FORBIDDEN, null); 
    }

    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    //O erro não é capturado pelo RestControllerAdvice porque é lançado antes da continuação da requisição passar pelos filtros, por isso
    //está sendo tratado especificamente aqui
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        var map = responseHandler.generateMap("Acesso negado", false, HttpStatus.FORBIDDEN, null);
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write( mapper.writeValueAsString(map));
    }

}
