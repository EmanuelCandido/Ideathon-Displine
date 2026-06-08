package com.sistemaIdeathon.displine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemaIdeathon.displine.DTO.AlunoDTO;
import com.sistemaIdeathon.displine.DTO.AlunoRequestDTO;
import com.sistemaIdeathon.displine.service.AlunoService;

import jakarta.validation.Valid;
//Define a classe como um controlador REST, respondendo requisições HTTP com JSON
@RestController
//Define o caminho base das rotas do controlador como "/Aluno"
@RequestMapping("/Aluno")
public class AlunoController {
    // Injeta automaticamente o serviço responsável pelas regras de negócio do Aluno
    @Autowired
    private AlunoService service;

    //Mapeia a requisição GET em "/Aluno", fazendo retornar os valores
    @GetMapping
    public List<AlunoDTO> listar() {
        return service.listar();
    }

    //Mapeia a requisição POST em "/Aluno", cadastrando os valores
    //@Valid valida os dados recebidos e @RequestBody converte o JSON da requisição para o DTO
    @PostMapping
    public AlunoDTO salvar(@Valid @RequestBody AlunoRequestDTO dto) {
        return service.salvar(dto);
    }

    //Mapeia a requisição PUT em "/Aluno{id}", atualizando os valores
    //@PathVariable captura o id diretamente da URL
    @PutMapping("/{id}")
    public AlunoDTO atualizar(@PathVariable Long id, @Valid @RequestBody AlunoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    //Mapeia a requisição DELETE em "/Aluno/{id}", retornando os valores
    //Retorna HTTP 204 (No Content) indicando que a operação foi realizada com sucesso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}