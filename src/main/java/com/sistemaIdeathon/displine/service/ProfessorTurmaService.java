package com.sistemaIdeathon.displine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaIdeathon.displine.DTO.ProfessorTurmaDTO;
import com.sistemaIdeathon.displine.entity.ProfessorTurma;
import com.sistemaIdeathon.displine.entity.ProfessorTurmaId;
import com.sistemaIdeathon.displine.repository.ProfessorTurmaRepository;

@Service
public class ProfessorTurmaService {

	@Autowired
	private ProfessorTurmaRepository repository;
	
	public List<ProfessorTurmaDTO> listar() {
		return repository.findAll().stream().map(ProfessorTurmaDTO::new).toList();
		}
	
	public ProfessorTurma salvar(ProfessorTurma professorTurma) {
		return repository.save(professorTurma);
	}
	
	public void deletar(Long idUsuario, Long idTurma) {
        ProfessorTurmaId id = new ProfessorTurmaId(idUsuario, idTurma);
        repository.deleteById(id);
    }

    public ProfessorTurma atualizar(Long idUsuario, Long idTurma) {
        ProfessorTurmaId id = new ProfessorTurmaId(idUsuario, idTurma);
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));
	
	
}
}
