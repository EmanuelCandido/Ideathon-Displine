package com.sistemaIdeathon.displine.DTO;

import java.time.LocalDate;

import com.sistemaIdeathon.displine.entity.Aluno;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlunoDTO {

	private Long id;

	private String nome;

	private String email;

	private String matricula;

	private String turma;

	private String curso;

	private String tipoUsuario;

	private LocalDate dataCadastro;

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