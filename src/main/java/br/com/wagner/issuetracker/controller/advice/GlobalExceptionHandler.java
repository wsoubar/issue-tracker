package br.com.wagner.issuetracker.controller.advice;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Essa anotação transforma a classe num "Interceptador Global" para todos os RestControllers
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Diz ao Spring: "Sempre que uma RuntimeException estourar em qualquer lugar,
    // caia aqui"
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessExceptions(RuntimeException ex) {

        // Montamos um JSON de erro limpo, padronizado e seguro (sem stack traces)
        Map<String, Object> errorResponse = Map.of(
                "timestamp", Instant.now(),
                "status", HttpStatus.UNPROCESSABLE_CONTENT.value(),
                "error", "Erro de Validação de Negócio",
                "message", ex.getMessage() // "Regra de negócio violada! ..."
        );

        // Retornamos HTTP 422 em vez do temido 500
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(errorResponse);
    }
}