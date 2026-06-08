package com.sistemaIdeathon.displine.Exception;

// Exceção personalizada lançada quando um recurso solicitado não é encontrado no sistema
// Estende RuntimeException, ou seja, não precisa ser declarada ou capturada obrigatoriamente
public class RecursoNaoEncontradoException extends RuntimeException {
    // Construtor que recebe uma mensagem descrevendo qual recurso não foi encontrado
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}