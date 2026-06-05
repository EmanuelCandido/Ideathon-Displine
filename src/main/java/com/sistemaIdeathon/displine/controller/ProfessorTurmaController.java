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

import com.sistemaIdeathon.displine.DTO.ProfessorTurmaDTO;
import com.sistemaIdeathon.displine.entity.ProfessorTurma;
import com.sistemaIdeathon.displine.service.ProfessorTurmaService;

@RestController
@RequestMapping("/ProfessorTurma")
public class ProfessorTurmaController {

	@Autowired
    private ProfessorTurmaService service;

    @GetMapping
    public List<ProfessorTurmaDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public ProfessorTurma salvar(@RequestBody ProfessorTurma professorTurma) {
        return service.salvar(professorTurma);
    }

    @PutMapping("/{idUsuario}/{idTurma}")
    public ProfessorTurma atualizar(@PathVariable Long idUsuario,
            @PathVariable Long idTurma, @RequestBody ProfessorTurma professorTurma) {
    	return service.atualizar(idUsuario,idTurma);
    }

    @DeleteMapping("/{idUsuario}/{idTurma}")
    public void deletar(@PathVariable Long idUsuario,
            @PathVariable Long idTurma) {
        service.deletar(idUsuario,idTurma);
    
	
}
	
	
}
