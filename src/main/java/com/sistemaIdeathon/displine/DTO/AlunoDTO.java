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

	    private String matricula;

	    private String curso;

	    private String tipoUsuario;

	    private LocalDate dataCadastro;

	    private Long idTurma;
	    public AlunoDTO(Aluno aluno) {
	        this.id = aluno.getIdUsuario();
	    	this.nome = aluno.getNome();
	        this.matricula = aluno.getMatricula();
	        this.curso = aluno.getCurso();
	        this.tipoUsuario = aluno.getTipoUsuario();
	        this.dataCadastro = aluno.getDataCadastro();
	        this.idTurma = aluno.getTurma() != null ? aluno.getTurma().getIdTurma() : null;
	    }
	
	
	
	
}
