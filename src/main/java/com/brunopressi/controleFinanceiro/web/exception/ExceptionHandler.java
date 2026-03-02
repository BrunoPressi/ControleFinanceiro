package com.brunopressi.controleFinanceiro.web.exception;

import com.brunopressi.controleFinanceiro.exception.DuplicateEntityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<ErrorMessage> duplicateEntityException (RuntimeException e, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                LocalDateTime.now(),
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(HttpServletRequest request, BindingResult bindingResult) {
        ErrorMessage errorMessage = new ErrorMessage(
                LocalDateTime.now(),
                "Campos inválidos",
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                bindingResult
        );

        return ResponseEntity.badRequest().body(errorMessage);
    }

}
