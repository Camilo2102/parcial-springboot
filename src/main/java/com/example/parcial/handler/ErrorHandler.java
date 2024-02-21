package com.example.parcial.handler;

import com.example.parcial.exceptions.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({HttpException.class})
    public ResponseEntity<Map<String, String>> handleException(HttpException ex) {
        Map<String, String> response = Map.of("error", ex.getMessage(), "status", "rejected");

        return new ResponseEntity<>(response, ex.getHttpCode());
    }
}
