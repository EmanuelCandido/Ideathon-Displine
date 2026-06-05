package com.sistemaIdeathon.displine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaIdeathon.displine.entity.Usuario;
import com.sistemaIdeathon.displine.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> listar() {
		return repository.findAll();
		}
	
	public Usuario salvar(Usuario usuario) {
		return repository.save(usuario);
	}
	
	public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Usuario atualizar(Long id, Usuario usuario) {

        usuario.setId(id);

        return repository.save(usuario);
    }
	
	
	
}
