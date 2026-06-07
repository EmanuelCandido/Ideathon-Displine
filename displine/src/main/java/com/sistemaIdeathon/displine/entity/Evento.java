package com.sistemaIdeathon.displine.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Evento")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvento;

	private LocalDate dataEvento;

	private String titulo;

	private String descricao;

	private LocalTime horarioInicio;

	private LocalTime horarioFim;

	private String tipoEvento;

	private String prioridade;

	private String materiaRelacionada;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
}