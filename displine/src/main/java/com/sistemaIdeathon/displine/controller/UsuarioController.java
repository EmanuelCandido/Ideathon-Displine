package com.sistemaIdeathon.displine.controller;

import java.util.List;

import com.sistemaIdeathon.displine.DTO.LoginRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemaIdeathon.displine.DTO.UsuarioDTO;
import com.sistemaIdeathon.displine.DTO.UsuarioRequestDTO;
import com.sistemaIdeathon.displine.service.UsuarioService;

import jakarta.validation.Valid;
// Define que essa classe é um controlador REST, respondendo requisições HTTP com JSON
@RestController
// Define o caminho base das rotas desse controlador como "/Usuario"
@RequestMapping("/Usuario")
public class UsuarioController {
	// Injeta automaticamente o serviço responsável pelas regras de negócio do Usuario
	@Autowired
	private UsuarioService service;

	// Mapeia requisições POST em "/Usuario/login" — realiza a autenticação do usuário
	// @Valid valida os dados recebidos e @RequestBody converte o JSON da requisição para o DTO
	@PostMapping("/login")
	public UsuarioDTO login(@Valid @RequestBody LoginRequestDTO dto) {
		return service.login(dto);
	}

	// Mapeia requisições GET em "/Usuario" — retorna a lista de todos os usuários
	@GetMapping
	public List<UsuarioDTO> listar() {
		return service.listar();
	}

	// Mapeia requisições POST em "/Usuario" — cadastra um novo usuário
	// @Valid valida os dados recebidos e @RequestBody converte o JSON da requisição para o DTO
	@PostMapping
	public UsuarioDTO salvar(@Valid @RequestBody UsuarioRequestDTO dto) {
		return service.salvar(dto);
	}

	// Mapeia requisições PUT em "/Usuario/{id}" — atualiza os dados de um usuário existente pelo id
	// @PathVariable captura o id diretamente da URL
	@PutMapping("/{id}")
	public UsuarioDTO atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {
		return service.atualizar(id, dto);
	}
	// Mapeia requisições DELETE em "/Usuario/{id}" — remove um usuário pelo id
	// Retorna HTTP 204 (No Content) indicando que a operação foi realizada com sucesso
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}