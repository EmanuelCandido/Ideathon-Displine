package com.sistemaIdeathon.displine.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProfessorTurmaId implements Serializable {

	private Long idUsuario;

    private Long idTurma;
}
