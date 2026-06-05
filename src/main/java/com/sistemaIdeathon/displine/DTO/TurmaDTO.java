package com.sistemaIdeathon.displine.DTO;

import com.sistemaIdeathon.displine.entity.Turma;

public class TurmaDTO {

	    private Long idTurma;

	    private String nomeTurma;

	    private int anoLetivo;

	    public TurmaDTO(Turma turma) {
	        this.idTurma = turma.getIdTurma();
	        this.nomeTurma = turma.getNomeTurma();
	        this.anoLetivo = turma.getAnoLetivo();
	    }
	
	
	}

