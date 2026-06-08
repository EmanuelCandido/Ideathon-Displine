package com.example.displine;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventoItem {

    private Long idEvento;
    private String titulo;
    private String descricao;
    private String tipoEvento;
    private String prioridade;
    private String materiaRelacionada;
    private LocalDate dataEvento;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;

    public EventoItem() {
    }

    public EventoItem(Long idEvento, String titulo, String descricao, String tipoEvento, String prioridade,
                      String materiaRelacionada, LocalDate dataEvento, LocalTime horarioInicio, LocalTime horarioFim) {
        this.idEvento = idEvento;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipoEvento = tipoEvento;
        this.prioridade = prioridade;
        this.materiaRelacionada = materiaRelacionada;
        this.dataEvento = dataEvento;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getMateriaRelacionada() {
        return materiaRelacionada;
    }

    public void setMateriaRelacionada(String materiaRelacionada) {
        this.materiaRelacionada = materiaRelacionada;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }
}