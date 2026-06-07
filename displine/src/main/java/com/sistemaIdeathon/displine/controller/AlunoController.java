package com.sistemaIdeathon.displine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemaIdeathon.displine.DTO.AlunoDTO;
import com.sistemaIdeathon.displine.DTO.AlunoRequestDTO;
import com.sistemaIdeathon.displine.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Aluno")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public List<AlunoDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public AlunoDTO salvar(@Valid @RequestBody AlunoRequestDTO dto) {
        return service.salvar(dto);
    }

    @PutMapping("/{id}")
    public AlunoDTO atualizar(@PathVariable Long id, @Valid @RequestBody AlunoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}