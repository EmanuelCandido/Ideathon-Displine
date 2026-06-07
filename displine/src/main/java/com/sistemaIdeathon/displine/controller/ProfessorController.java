package com.sistemaIdeathon.displine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemaIdeathon.displine.DTO.ProfessorDTO;
import com.sistemaIdeathon.displine.DTO.ProfessorRequestDTO;
import com.sistemaIdeathon.displine.service.ProfessorService;

import jakarta.validation.Valid;

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
    public ProfessorDTO salvar(@Valid @RequestBody ProfessorRequestDTO dto) {
        return service.salvar(dto);
    }

    @PutMapping("/{id}")
    public ProfessorDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProfessorRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}