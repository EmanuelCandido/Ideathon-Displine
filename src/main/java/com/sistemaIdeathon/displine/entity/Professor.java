package com.sistemaIdeathon.displine.entity;

import jakarta.persistence.Entity;
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
@Table(name="Professor")
@PrimaryKeyJoinColumn(name = "id")
public class Professor extends Usuario {

	private String materia;
	
	private String turma;
	
	
	
}
