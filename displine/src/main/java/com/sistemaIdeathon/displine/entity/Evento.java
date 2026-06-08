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
//Comandos pertencentes a biblioteca do Lombok, usada na automação de getters, setters e construtores
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Indicando que essa classe é uma entidade JPA mapeada no banco de dados
@Entity
// Define que a tabela correspondente no banco de dados se chama "Evento"
@Table(name = "Evento")
public class Evento {
	// Indica que idEvento é a chave primária da tabela
	@Id
	// Define que o valor do id será gerado automaticamente pelo banco de dados
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long idEvento;
	//Data que irá ocorrer o evento
	private LocalDate dataEvento;
	//Título do evento
	private String titulo;
	//Descrição do evento
	private String descricao;
	//Especificação do horário do início
	private LocalTime horarioInicio;
	//Especificação do horario final
	private LocalTime horarioFim;
	//Diz qual o tipo de evento que irá ocorrer
	private String tipoEvento;
	//Define a prioridade do evento em níveis(baixa,media,alta)
	private String prioridade;
	//Matéria que o evento está relacionada
	private String materiaRelacionada;

	// Relacionamento muitos-para-um: vários eventos podem pertencer a um mesmo usuário
	@ManyToOne
	// Define a coluna "id_usuario" como chave estrangeira na tabela Evento
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
}