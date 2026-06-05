package com.sistemaIdeathon.displine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaIdeathon.displine.entity.Usuario;
import com.sistemaIdeathon.displine.service.UsuarioService;
@RestController
@RequestMapping("/Usuario")
public class UsuarioController {

		@Autowired
	    private UsuarioService service;

	    @GetMapping
	    public List<Usuario> listar() {
	        return service.listar();
	    }

	    @PostMapping
	    public Usuario salvar(@RequestBody Usuario usuario) {
	        return service.salvar(usuario);
	    }

	    @PutMapping("/{id}")
	    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
	    	return service.atualizar(id, usuario);
	    }

	    @DeleteMapping("/{id}")
	    public void deletar(@PathVariable Long id) {
	        service.deletar(id);
	    
	
	

	    }
}