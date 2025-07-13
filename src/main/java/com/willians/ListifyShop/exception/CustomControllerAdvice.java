package com.willians.ListifyShop.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<MessageError> error(ResponseStatusException e, HttpServletRequest request){
        int ponto = e.getMessage().indexOf("\"") - 1;
        int pontoFim = e.getMessage().length() - 1;
        String error = e.getMessage().substring(0, ponto);
        String message = e.getMessage().substring(ponto + 2, pontoFim);
        return ResponseEntity.status(e.getStatusCode()).body(
                new MessageError(
                        Instant.now(),
                        e.getStatusCode().value(),
                        error,
                        message,
                        request.getRequestURI()
                        ));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageError> notFoundExceptionResponseEntity(NotFoundException e, HttpServletRequest request){
        int cod = HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(cod).body(
                new MessageError(
                    Instant.now(),
                    cod,
                    "Recurso não encontrado",
                    e.getMessage(),
                    request.getRequestURI()
                    ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> anotationError(MethodArgumentNotValidException e, HttpServletRequest request){
        int cod = HttpStatus.UNPROCESSABLE_ENTITY.value();
        ValidationError error = new ValidationError(
                Instant.now(),
                cod,
                "Validation exception",
                e.getMessage(),
                request.getRequestURI()
        );

        e.getBindingResult().getFieldErrors().stream().forEach(field -> error.addError(field.getField(), field.getDefaultMessage()));

        return ResponseEntity.status(cod).body(error);
    }
}
