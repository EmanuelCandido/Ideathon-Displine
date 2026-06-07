package com.sistemaIdeathon.displine.Exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErroResposta {

    private LocalDateTime dataHora;
    private int status;
    private String erro;
    private List<String> mensagens;

    public ErroResposta(LocalDateTime dataHora, int status, String erro, List<String> mensagens) {
        this.dataHora = dataHora;
        this.status = status;
        this.erro = erro;
        this.mensagens = mensagens;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public int getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

    public List<String> getMensagens() {
        return mensagens;
    }
}