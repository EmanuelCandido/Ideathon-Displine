package com.sistemaIdeathon.displine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaIdeathon.displine.DTO.TurmaDTO;
import com.sistemaIdeathon.displine.entity.Turma;
import com.sistemaIdeathon.displine.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repository;
	
	public List<TurmaDTO> listar() {
		return repository.findAll().stream().map(TurmaDTO::new).toList();
		}
	
	public Turma salvar(Turma turma) {
		return repository.save(turma);
	}
	
	public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Turma atualizar(Long id, Turma turma) {

    	turma.setIdTurma(id);

        return repository.save(turma);
    }
	
}
