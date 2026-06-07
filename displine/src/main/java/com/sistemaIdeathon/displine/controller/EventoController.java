package com.sistemaIdeathon.displine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemaIdeathon.displine.DTO.EventoDTO;
import com.sistemaIdeathon.displine.DTO.EventoRequestDTO;
import com.sistemaIdeathon.displine.service.EventoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Evento")
public class EventoController {

	@Autowired
	private EventoService service;

	@GetMapping
	public List<EventoDTO> listar() {
		return service.listar();
	}

	@GetMapping("/usuario/{idUsuario}")
	public List<EventoDTO> listarPorUsuario(@PathVariable Long idUsuario) {
		return service.listarPorUsuario(idUsuario);
	}

	@PostMapping
	public EventoDTO salvar(@Valid @RequestBody EventoRequestDTO dto) {
		return service.salvar(dto);
	}

	@PutMapping("/{id}")
	public EventoDTO atualizar(@PathVariable Long id, @Valid @RequestBody EventoRequestDTO dto) {
		return service.atualizar(id, dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}