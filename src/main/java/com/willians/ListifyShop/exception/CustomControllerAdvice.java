package com.willians.ListifyShop.exception;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Timer;

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
}
