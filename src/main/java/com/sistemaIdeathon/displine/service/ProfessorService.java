package com.sistemaIdeathon.displine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaIdeathon.displine.DTO.ProfessorDTO;
import com.sistemaIdeathon.displine.entity.Professor;
import com.sistemaIdeathon.displine.repository.ProfessorRepository;


@Service
public class ProfessorService {

	@Autowired
    private ProfessorRepository repository;
	
	public List<ProfessorDTO> listar() {
        return repository.findAll().stream().map(ProfessorDTO::new).toList();
    }

	public Professor salvar(Professor professor) {
	        return repository.save(professor);
	}

	public void deletar(Long id) {
        repository.deleteById(id);
    }

	public Professor atualizar(Long id, Professor professor) {

        professor.setIdUsuario(id);
        
        return repository.save(professor);
    }
	
	
}
