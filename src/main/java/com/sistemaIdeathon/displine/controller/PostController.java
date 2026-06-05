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

import com.sistemaIdeathon.displine.DTO.PostDTO;
import com.sistemaIdeathon.displine.entity.Post;
import com.sistemaIdeathon.displine.service.PostService;

@RestController
@RequestMapping("/Post")
public class PostController {

	@Autowired
    private PostService service;

    @GetMapping
    public List<PostDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public Post salvar(@RequestBody Post post) {
        return service.salvar(post);
    }

    @PutMapping("/{id}")
    public Post atualizar(@PathVariable Long id, @RequestBody Post post) {
    	return service.atualizar(id, post);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    
	
}
	
}
