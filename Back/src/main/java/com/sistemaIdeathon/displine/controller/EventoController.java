package com.sistemaIdeathon.displine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemaIdeathon.displine.DTO.EventoDTO;
import com.sistemaIdeathon.displine.DTO.EventoRequestDTO;
import com.sistemaIdeathon.displine.service.EventoService;

import jakarta.validation.Valid;
// Define que essa classe é um controlador REST, respondendo requisições HTTP com JSON
@RestController
// Define o caminho base das rotas desse controlador como "/Evento"
@RequestMapping("/Evento")
public class EventoController {
	// Injeta automaticamente o serviço responsável pelas regras de negócio do Evento
	@Autowired
	private EventoService service;

	// Mapeia requisições GET em "/Evento" — retorna a lista de todos os eventos
	@GetMapping
	public List<EventoDTO> listar() {
		return service.listar();
	}

	// Mapeia requisições GET em "/Evento/usuario/{idUsuario}" — retorna todos os eventos pertencentes a um usuário específico, identificado pelo idUsuario na URL
	@GetMapping("/usuario/{idUsuario}")
	public List<EventoDTO> listarPorUsuario(@PathVariable Long idUsuario) {
		return service.listarPorUsuario(idUsuario);
	}

	// Mapeia requisições POST em "/Evento" — cadastra um novo evento
	//@Valid valida os dados recebidos e @RequestBody converte o JSON da requisição para o DTO
	@PostMapping
	public EventoDTO salvar(@Valid @RequestBody EventoRequestDTO dto) {
		return service.salvar(dto);
	}

	// Mapeia requisições PUT em "/Evento/{id}" — atualiza os dados de um evento existente pelo id
	// @PathVariable captura o id diretamente da URL
	@PutMapping("/{id}")
	public EventoDTO atualizar(@PathVariable Long id, @Valid @RequestBody EventoRequestDTO dto) {
		return service.atualizar(id, dto);
	}
	// Mapeia requisições DELETE em "/Evento/{id}" — remove um evento pelo id
	// Retorna HTTP 204 (No Content) indicando que a operação foi realizada com sucesso
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}