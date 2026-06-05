package com.sistemaIdeathon.displine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaIdeathon.displine.DTO.ComentarioDTO;
import com.sistemaIdeathon.displine.entity.Aluno;
import com.sistemaIdeathon.displine.entity.Comentario;
import com.sistemaIdeathon.displine.repository.ComentarioRepository;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository repository;
	
	public List<ComentarioDTO> listar() {
		return repository.findAll().stream().map(ComentarioDTO::new).toList();
		}
	
	public Comentario salvar(Comentario comentario) {
		return repository.save(comentario);
	}
	
	public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Comentario atualizar(Long id, Comentario comentario) {

    	comentario.setIdComentario(id);

        return repository.save(comentario);
    }
	
	
}
