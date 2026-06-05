package com.sistemaIdeathon.displine.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import com.sistemaIdeathon.displine.entity.Evento;

public class EventoDTO {

private LocalDate dataEvento;
	
	private String titulo;
	
	private String descricao;
	
	private LocalTime horarioInicio;
	
	private LocalTime horarioFim;
	
	private String tipoEvento;
	
	private String prioridade;
	
	private String materiaRelacionada;
	
	public EventoDTO(Evento evento) {
	    this.titulo = evento.getTitulo();
	    this.descricao = evento.getDescricao();
	    this.horarioInicio = evento.getHorarioInicio();
	    this.horarioFim = evento.getHorarioFim();
	    this.tipoEvento = evento.getTipoEvento();
	    this.prioridade = evento.getPrioridade();
	    this.materiaRelacionada = evento.getMateriaRelacionada();
	}
	
	
	
}
