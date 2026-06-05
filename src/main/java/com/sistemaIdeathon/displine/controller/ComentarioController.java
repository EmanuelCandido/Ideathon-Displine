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

import com.sistemaIdeathon.displine.DTO.ComentarioDTO;
import com.sistemaIdeathon.displine.entity.Comentario;
import com.sistemaIdeathon.displine.service.ComentarioService;

@RestController
@RequestMapping("/Comentario")
public class ComentarioController {

	@Autowired
    private ComentarioService service;

    @GetMapping
    public List<ComentarioDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public Comentario salvar(@RequestBody Comentario comentario) {
        return service.salvar(comentario);
    }

    @PutMapping("/{id}")
    public Comentario atualizar(@PathVariable Long id, @RequestBody Comentario comentario) {
    	return service.atualizar(id, comentario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
	
	
}
}
