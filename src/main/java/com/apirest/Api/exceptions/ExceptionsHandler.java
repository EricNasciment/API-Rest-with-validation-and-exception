package com.apirest.Api.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionsHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandartError> resourceNotfoundException
            (ResourceNotFoundException e,
             HttpServletRequest request){
        StandartError err = new StandartError();
        HttpStatus status = HttpStatus.NOT_FOUND;
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Recurso não encontrado ou entidade não existe");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
         return ResponseEntity.status(status.value()).body(err);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandartError> dataBaseException(
            DataBaseException e,
            HttpServletRequest request){
        StandartError err = new StandartError();
        HttpStatus status = HttpStatus.CONFLICT;
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Entidade não pode ser deletada,Conflito na DataBase");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);


    }
}
