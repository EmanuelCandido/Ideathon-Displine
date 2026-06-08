package com.sistemaIdeathon.displine.DTO;

import java.time.LocalDate;

import com.sistemaIdeathon.displine.entity.Professor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Comandos pertencentes à biblioteca do Lombok, usada na automação de getters, setters e construtor vazio
@Getter
@Setter
@NoArgsConstructor
// Classe DTO (Data Transfer Object) usada para trafegar os dados do Professor entre as camadas da aplicação
public class ProfessorDTO {

	// Identificador único do professor
	private Long id;

	// Nome completo do professor
	private String nome;

	// Endereço de e-mail do professor
	private String email;

	// Matéria lecionada pelo professor
	private String materia;

	// Turma em que o professor leciona
	private String turma;

	// Tipo do usuário no sistema
	private String tipoUsuario;

	// Data em que o professor foi cadastrado no sistema
	private LocalDate dataCadastro;

	// Construtor que converte uma entidade Professor em ProfessorDTO,
	// mapeando cada campo da entidade para o respectivo atributo do DTO
	public ProfessorDTO(Professor professor) {
		this.id = professor.getId();
		this.nome = professor.getNome();
		this.email = professor.getEmail();
		this.materia = professor.getMateria();
		this.turma = professor.getTurma();
		this.tipoUsuario = professor.getTipoUsuario();
		this.dataCadastro = professor.getDataCadastro();
	}
}