package com.sistemaIdeathon.displine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//Comandos do lombok de automação de comandos getter, setter e construtores
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//Indicando que essa classe é uma entidade JPA mapeada no banco de dados
@Entity
//Mapeia a tabela Aluno no banco de dados
@Table(name = "Aluno")
//Define a coluna "id" como chave primaria herdada da classe Usuario
@PrimaryKeyJoinColumn(name = "id")
public class Aluno extends Usuario {
	//Matricula de identificação do aluno
	private String matricula;
	//Turma em que o aluno está inserido
	private String turma;
	//Curso em que o aluno está matriculado
	private String curso;
}