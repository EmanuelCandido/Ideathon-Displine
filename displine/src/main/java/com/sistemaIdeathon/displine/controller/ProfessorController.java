package com.sistemaIdeathon.displine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemaIdeathon.displine.DTO.ProfessorDTO;
import com.sistemaIdeathon.displine.DTO.ProfessorRequestDTO;
import com.sistemaIdeathon.displine.service.ProfessorService;

import jakarta.validation.Valid;
// Define que essa classe é um controlador REST, respondendo requisições HTTP com JSON
@RestController
// Define o caminho base das rotas desse controlador como "/Professor"
@RequestMapping("/Professor")
public class ProfessorController {
    // Injeta automaticamente o serviço responsável pelas regras de negócio do Professor
    @Autowired
    private ProfessorService service;

    // Mapeia requisições GET em "/Professor" — retorna a lista de todos os professores
    @GetMapping
    public List<ProfessorDTO> listar() {
        return service.listar();
    }

    // Mapeia requisições POST em "/Professor" — cadastra um novo professor
    // @Valid valida os dados recebidos e @RequestBody converte o JSON da requisição para o DTO
    @PostMapping
    public ProfessorDTO salvar(@Valid @RequestBody ProfessorRequestDTO dto) {
        return service.salvar(dto);
    }

    // Mapeia requisições PUT em "/Professor/{id}" — atualiza os dados de um professor existente pelo id
    // @PathVariable captura o id diretamente da URL
    @PutMapping("/{id}")
    public ProfessorDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProfessorRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    // Mapeia requisições DELETE em "/Professor/{id}" — remove um professor pelo id
    // Retorna HTTP 204 (No Content) indicando que a operação foi realizada com sucesso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}