package com.sistemaIdeathon.displine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//Comandos pertencentes a biblioteca do Lombok, usada na automação de getters, setters e construtores
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Indicando que essa classe é uma entidade JPA mapeada no banco de dados
@Entity
// Define que a tabela correspondente no banco de dados se chama "Professor"
@Table(name = "Professor")
//Define a coluna "id" como chave primaria herdada da classe Usuario
@PrimaryKeyJoinColumn(name = "id")
public class Professor extends Usuario {
	//Matéria em que o professor está relacionada
	private String materia;
	//Turma(s) em que o professor está lecionando
	private String turma;
}