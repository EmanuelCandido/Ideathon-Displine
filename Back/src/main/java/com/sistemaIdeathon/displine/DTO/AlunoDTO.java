package com.sistemaIdeathon.displine.DTO;

import java.time.LocalDate;

import com.sistemaIdeathon.displine.entity.Aluno;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Comandos pertencentes à biblioteca do Lombok, usada na automação de getters, setters e construtor vazio
@Getter
@Setter
@NoArgsConstructor
// Classe DTO (Data Transfer Object) usada para trafegar os dados do Aluno entre as camadas da aplicação
public class AlunoDTO {

	// Identificador único do aluno
	private Long id;

	// Nome completo do aluno
	private String nome;

	// Endereço de e-mail do aluno
	private String email;

	// Matrícula de identificação do aluno na instituição
	private String matricula;

	// Turma em que o aluno está inserido
	private String turma;

	// Curso em que o aluno está matriculado
	private String curso;

	// Tipo do usuário no sistema
	private String tipoUsuario;

	// Data em que o aluno foi cadastrado no sistema
	private LocalDate dataCadastro;

	// Construtor que converte uma entidade Aluno em AlunoDTO, mapeando cada campo da entidade para o respectivo atributo do DTO
	public AlunoDTO(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getNome();
		this.email = aluno.getEmail();
		this.matricula = aluno.getMatricula();
		this.turma = aluno.getTurma();
		this.curso = aluno.getCurso();
		this.tipoUsuario = aluno.getTipoUsuario();
		this.dataCadastro = aluno.getDataCadastro();
	}
}