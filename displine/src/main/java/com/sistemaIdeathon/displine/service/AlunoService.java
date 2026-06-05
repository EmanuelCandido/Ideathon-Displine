package com.sistemaIdeathon.displine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaIdeathon.displine.DTO.AlunoDTO;
import com.sistemaIdeathon.displine.entity.Aluno;
import com.sistemaIdeathon.displine.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;
	
	public List<AlunoDTO> listar() {
		return repository.findAll().stream().map(AlunoDTO::new).toList();
		}
	
	public Aluno salvar(Aluno aluno) {
		return repository.save(aluno);
	}
	
	public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Aluno atualizar(Long id, Aluno aluno) {

        aluno.setId(id);

        return repository.save(aluno);
    }
	
}
