package com.brunopressi.controleFinanceiro.web.exception;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@Getter
public class ErrorMessage {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private LocalDateTime timestamp;
    private String message;
    private int status;
    private String error;
    private String path;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private HashMap<String, String> fieldErrors = new HashMap<>();

    public ErrorMessage(LocalDateTime timestamp, String message, int status, String path, String error) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.path = path;
        this.error = error;
    }

    public ErrorMessage(LocalDateTime timestamp, String message, int status, String path, String error, BindingResult bindingResult) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.path = path;
        this.error = error;
        addErrors(bindingResult);
    }

    private void addErrors(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();

        for (FieldError error: errors) {
            this.fieldErrors.put(error.getField(), error.getDefaultMessage());
        }
    }
}
