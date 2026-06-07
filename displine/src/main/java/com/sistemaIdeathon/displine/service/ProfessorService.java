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

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public List<ProfessorDTO> listar() {
        return repository.findAll().stream().map(ProfessorDTO::new).toList();
    }

    public ProfessorDTO salvar(ProfessorRequestDTO dto) {
        Professor professor = new Professor();

        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setMateria(dto.getMateria());
        professor.setTurma(dto.getTurma());
        professor.setTipoUsuario("Professor");
        professor.setDataCadastro(LocalDate.now());

        Professor professorSalvo = repository.save(professor);

        return new ProfessorDTO(professorSalvo);
    }

    public ProfessorDTO atualizar(Long id, ProfessorRequestDTO dto) {
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

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Professor não encontrado com id: " + id);
        }

        repository.deleteById(id);
    }
}