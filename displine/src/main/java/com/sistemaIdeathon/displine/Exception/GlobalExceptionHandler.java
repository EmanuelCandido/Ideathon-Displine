package com.sistemaIdeathon.displine.Exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
// Define que essa classe trata exceções globalmente para todos os controladores REST da aplicação
@RestControllerAdvice
public class GlobalExceptionHandler {
    // Intercepta exceções do tipo RecursoNaoEncontradoException lançadas em qualquer controlador
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        // Monta a resposta de erro com status HTTP 404 (Not Found) e a mensagem da exceção
        ErroResposta erro = new ErroResposta(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                List.of(ex.getMessage())
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    // Intercepta exceções de validação lançadas quando os dados enviados pelo cliente são inválidos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarErroValidacao(MethodArgumentNotValidException ex) {
        // Coleta todos os erros de campo e formata cada um como "campo: mensagem"
        List<String> mensagens = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();
        // Monta a resposta de erro com status HTTP 400 (Bad Request) e a lista de mensagens de validação
        ErroResposta erro = new ErroResposta(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                mensagens
        );

        return ResponseEntity.badRequest().body(erro);
    }
    // Intercepta qualquer outra exceção não tratada pelos métodos anteriores
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> tratarErroGeral(Exception ex) {
        // Monta a resposta de erro com status HTTP 500 (Internal Server Error) e mensagem genérica
        ErroResposta erro = new ErroResposta(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno",
                List.of("Ocorreu um erro inesperado no sistema.")
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}