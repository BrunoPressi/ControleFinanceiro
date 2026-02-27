package com.brunopressi.controleFinanceiro.web.exception;

import com.brunopressi.controleFinanceiro.exception.DuplicateEntityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class exceptionHandler {

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<ErrorMessage> duplicateEntityException (RuntimeException e, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setMessage(e.getMessage());
        errorMessage.setStatus(HttpStatus.CONFLICT.value());
        errorMessage.setError(HttpStatus.CONFLICT.getReasonPhrase());
        errorMessage.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

}
