package com.sistemaIdeathon.displine.DTO;

import java.time.LocalDate;

import com.sistemaIdeathon.displine.entity.Professor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfessorDTO {

	private String materia;
	
	private String turma;
	
	private String nome;
	
	private String email;
	
	private String tipoUsuario;
	
	private LocalDate dataCadastro;
	
	public ProfessorDTO(Professor professor) {
        this.nome = professor.getNome();
        this.materia = professor.getMateria();
        this.turma = professor.getTurma();
        this.tipoUsuario = professor.getTipoUsuario();
        this.dataCadastro = professor.getDataCadastro();
    }

}
