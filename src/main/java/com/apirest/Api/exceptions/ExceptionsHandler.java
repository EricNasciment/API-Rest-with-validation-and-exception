package com.apirest.Api.exceptions;

import com.apirest.Api.validation.FieldMessage;
import com.apirest.Api.validation.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        err.setError("Conflito no Banco de dados");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);


    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandartError> methodArgumentNotValid(
            ConstraintViolationException e,
            HttpServletRequest requestst){
        ValidationError err = new ValidationError();
        HttpStatus status =  HttpStatus.UNPROCESSABLE_ENTITY;
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Dados invaliddos ");
        err.setPath(requestst.getRequestURI());


        for(ConstraintViolation<?> violation: e.getConstraintViolations()){
            err.addError( violation.getPropertyPath().toString(),
                    violation.getMessage());
        }
        return  ResponseEntity.status(status.value()).body(err);
    }
}
