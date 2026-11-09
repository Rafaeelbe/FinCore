package com.fincore.fincore.controller;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> tratarValidação(MethodArgumentNotValidException exception) {

        List<String> erros = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest().body(erros);
    }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<String> tratarErroRegraNegocio(
                IllegalArgumentException exception) {

            return ResponseEntity.badRequest().body(exception.getMessage());
        }

}
