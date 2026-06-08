package com.sistemaIdeathon.displine.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
// Indica que essa classe é uma entidade JPA mapeada no banco de dados
@Entity
// Define que a tabela correspondente no banco de dados se chama "Usuario"
@Table(name = "Usuario")
//Define a estratégia de herança como JOINED: cada subclasse terá sua própria tabela, unida à tabela "Usuario" pela chave primária
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
	//Indica que o Id será a chave primária
	@Id
	//Define que o valor do Id será gerado automaticamente
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//Nome completo do usuáiro
	private String nome;
	//Email de login usado pelo usuáio
	private String email;
	//Senha que será salva pelo usuario
	private String senha;
	//Especificação do tipo do usuário(Professor, Aluno)
	private String tipoUsuario;
	//Data em que o usuário foi cadastrado no sistema
	private LocalDate dataCadastro;
}