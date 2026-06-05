package com.sistemaIdeathon.displine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
@Table(name="Aluno")
@PrimaryKeyJoinColumn(name = "id")
public class Aluno extends Usuario {
	
	@OneToOne
	@JoinColumn(name = "id_turma")
	private Turma turma;
	
	private String matricula;
	
	private String curso;
}
