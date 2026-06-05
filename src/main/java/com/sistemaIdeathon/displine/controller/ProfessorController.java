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

import com.sistemaIdeathon.displine.DTO.ProfessorDTO;
import com.sistemaIdeathon.displine.entity.Professor;
import com.sistemaIdeathon.displine.service.ProfessorService;

@RestController
@RequestMapping("/Professor")
public class ProfessorController {

	@Autowired
    private ProfessorService service;
  
    @GetMapping
    public List<ProfessorDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public Professor salvar(@RequestBody Professor professor) {
        return service.salvar(professor);
    }

    @PutMapping("/{id}")
    public Professor atualizar(@PathVariable Long id,
                                @RequestBody Professor professor) {

        return service.atualizar(id, professor);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
	
	
	
}
