package com.sistemaIdeathon.displine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaIdeathon.displine.entity.ProfessorTurma;
import com.sistemaIdeathon.displine.entity.ProfessorTurmaId;

public interface ProfessorTurmaRepository extends JpaRepository<ProfessorTurma,ProfessorTurmaId> {

}
