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

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PostMapping("/login")
	public UsuarioDTO login(@Valid @RequestBody LoginRequestDTO dto) {
		return service.login(dto);
	}

	@GetMapping
	public List<UsuarioDTO> listar() {
		return service.listar();
	}

	@PostMapping
	public UsuarioDTO salvar(@Valid @RequestBody UsuarioRequestDTO dto) {
		return service.salvar(dto);
	}

	@PutMapping("/{id}")
	public UsuarioDTO atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {
		return service.atualizar(id, dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}