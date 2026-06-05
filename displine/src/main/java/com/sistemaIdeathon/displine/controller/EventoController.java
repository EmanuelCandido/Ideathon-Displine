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

import com.sistemaIdeathon.displine.DTO.EventoDTO;
import com.sistemaIdeathon.displine.entity.Evento;
import com.sistemaIdeathon.displine.service.EventoService;

@RestController
@RequestMapping("/Evento")
public class EventoController {

	 @Autowired
	    private EventoService service;
	  
	    @GetMapping
	    public List<EventoDTO> listar() {
	        return service.listar();
	    }

	    @PostMapping
	    public Evento salvar(@RequestBody Evento professor) {
	        return service.salvar(professor);
	    }

	    @PutMapping("/{id}")
	    public Evento atualizar(@PathVariable Long id,
	                                @RequestBody Evento professor) {

	        return service.atualizar(id, professor);
	    }

	    @DeleteMapping("/{id}")
	    public void deletar(@PathVariable Long id) {
	        service.deletar(id);
	    }
	
}
