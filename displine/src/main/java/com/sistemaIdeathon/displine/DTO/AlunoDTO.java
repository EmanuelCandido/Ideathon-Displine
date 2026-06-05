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

	 	private String nome;

	    private String matricula;

	    private String turma;

	    private String curso;

	    private String tipoUsuario;

	    private LocalDate dataCadastro;

	    public AlunoDTO(Aluno aluno) {
	        this.nome = aluno.getNome();
	        this.matricula = aluno.getMatricula();
	        this.turma = aluno.getTurma();
	        this.curso = aluno.getCurso();
	        this.tipoUsuario = aluno.getTipoUsuario();
	        this.dataCadastro = aluno.getDataCadastro();
	    }
	
	
	
	
}
