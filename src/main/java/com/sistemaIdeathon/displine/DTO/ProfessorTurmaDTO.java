package com.sistemaIdeathon.displine.DTO;

import com.sistemaIdeathon.displine.entity.ProfessorTurma;

public class ProfessorTurmaDTO {

	  private Long idUsuario;
	    private Long idTurma;

	    public ProfessorTurmaDTO(ProfessorTurma professorTurma) {
	        this.idUsuario = professorTurma.getUsuario() != null
	                ? professorTurma.getUsuario().getIdUsuario()
	                : null;

	        this.idTurma = professorTurma.getTurma() != null
	                ? professorTurma.getTurma().getIdTurma()
	                : null;
	    }
	
}
