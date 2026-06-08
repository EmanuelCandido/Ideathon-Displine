package com.example.displine;

public class EventoApiDTO {

    private Long idEvento;
    private Long idUsuario;
    private String dataEvento;
    private String titulo;
    private String descricao;
    private String horarioInicio;
    private String horarioFim;
    private String tipoEvento;
    private String prioridade;
    private String materiaRelacionada;

    public Long getIdEvento() {
        return idEvento;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public String getMateriaRelacionada() {
        return materiaRelacionada;
    }
}