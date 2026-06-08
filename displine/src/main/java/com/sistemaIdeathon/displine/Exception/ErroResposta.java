package com.sistemaIdeathon.displine.Exception;

import java.time.LocalDateTime;
import java.util.List;
// Classe que representa o modelo de resposta de erro retornado pela API ao cliente
public class ErroResposta {

    // Data e hora em que o erro ocorreu
    private LocalDateTime dataHora;
    // Código HTTP do erro
    private int status;
    // Descrição resumida do erro
    private String erro;
    // Lista de mensagens detalhando os erros ocorridos
    private List<String> mensagens;
    // Construtor que inicializa todos os campos da resposta de erro
    public ErroResposta(LocalDateTime dataHora, int status, String erro, List<String> mensagens) {
        this.dataHora = dataHora;
        this.status = status;
        this.erro = erro;
        this.mensagens = mensagens;
    }
    // Retorna a data e hora em que o erro ocorreu
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    // Retorna o código HTTP do erro
    public int getStatus() {
        return status;
    }
    // Retorna a descrição resumida do erro
    public String getErro() {
        return erro;
    }
    // Retorna a lista de mensagens detalhando os erros ocorridos
    public List<String> getMensagens() {
        return mensagens;
    }
}