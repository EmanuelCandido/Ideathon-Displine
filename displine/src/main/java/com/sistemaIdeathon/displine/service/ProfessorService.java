package com.sistemaIdeathon.displine.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaIdeathon.displine.DTO.ProfessorDTO;
import com.sistemaIdeathon.displine.DTO.ProfessorRequestDTO;
import com.sistemaIdeathon.displine.entity.Professor;
import com.sistemaIdeathon.displine.Exception.RecursoNaoEncontradoException;
import com.sistemaIdeathon.displine.repository.ProfessorRepository;

// Define que essa classe é um serviço Spring, responsável pelas regras de negócio do Professor
@Service
public class ProfessorService {
    // Injeta automaticamente o repositório responsável pelo acesso aos dados do Professor
    @Autowired
    private ProfessorRepository repository;

    // Busca todos os professores no banco de dados e os converte para uma lista de ProfessorDTO
    public List<ProfessorDTO> listar() {
        return repository.findAll().stream().map(ProfessorDTO::new).toList();
    }

    // Cadastra um novo professor no banco de dados a partir dos dados recebidos no DTO
    public ProfessorDTO salvar(ProfessorRequestDTO dto) {
        Professor professor = new Professor();
        // Preenche os campos da entidade com os dados recebidos do cliente
        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setMateria(dto.getMateria());
        professor.setTurma(dto.getTurma());
        // Define o tipo do usuário fixamente como "Professor"
        professor.setTipoUsuario("Professor");
        // Define a data de cadastro como a data atual
        professor.setDataCadastro(LocalDate.now());

        Professor professorSalvo = repository.save(professor);

        return new ProfessorDTO(professorSalvo);
    }
    // Atualiza os dados de um professor existente identificado pelo id
    public ProfessorDTO atualizar(Long id, ProfessorRequestDTO dto) {
        // Busca o professor pelo id, lançando exceção caso não seja encontrado
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Professor não encontrado com id: " + id));

        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setMateria(dto.getMateria());
        professor.setTurma(dto.getTurma());
        professor.setTipoUsuario("Professor");

        Professor professorAtualizado = repository.save(professor);

        return new ProfessorDTO(professorAtualizado);
    }
    // Remove um professor do banco de dados pelo id
    public void deletar(Long id) {
        // Verifica se o professor existe antes de tentar deletar, lançando exceção caso não seja encontrado
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Professor não encontrado com id: " + id);
        }

        repository.deleteById(id);
    }
}