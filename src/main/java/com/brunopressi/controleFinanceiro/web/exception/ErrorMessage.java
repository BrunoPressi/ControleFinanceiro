package com.brunopressi.controleFinanceiro.web.exception;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
public class ErrorMessage {

    private LocalDateTime timestamp;
    private String message;
    private int status;
    private String error;
    private String path;

    public ErrorMessage(LocalDateTime timestamp, String message, int status, String path, String error) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.path = path;
        this.error = error;
    }
}
