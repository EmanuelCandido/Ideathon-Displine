package com.sistemaIdeathon.displine.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaIdeathon.displine.DTO.AlunoDTO;
import com.sistemaIdeathon.displine.DTO.AlunoRequestDTO;
import com.sistemaIdeathon.displine.entity.Aluno;
import com.sistemaIdeathon.displine.Exception.RecursoNaoEncontradoException;
import com.sistemaIdeathon.displine.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;

	public List<AlunoDTO> listar() {
		return repository.findAll().stream().map(AlunoDTO::new).toList();
	}

	public AlunoDTO salvar(AlunoRequestDTO dto) {
		Aluno aluno = new Aluno();

		aluno.setNome(dto.getNome());
		aluno.setEmail(dto.getEmail());
		aluno.setSenha(dto.getSenha());
		aluno.setMatricula(dto.getMatricula());
		aluno.setTurma(dto.getTurma());
		aluno.setCurso(dto.getCurso());
		aluno.setTipoUsuario("Aluno");
		aluno.setDataCadastro(LocalDate.now());

		Aluno alunoSalvo = repository.save(aluno);

		return new AlunoDTO(alunoSalvo);
	}

	public AlunoDTO atualizar(Long id, AlunoRequestDTO dto) {
		Aluno aluno = repository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado com id: " + id));

		aluno.setNome(dto.getNome());
		aluno.setEmail(dto.getEmail());
		aluno.setSenha(dto.getSenha());
		aluno.setMatricula(dto.getMatricula());
		aluno.setTurma(dto.getTurma());
		aluno.setCurso(dto.getCurso());
		aluno.setTipoUsuario("Aluno");

		Aluno alunoAtualizado = repository.save(aluno);

		return new AlunoDTO(alunoAtualizado);
	}

	public void deletar(Long id) {
		if (!repository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Aluno não encontrado com id: " + id);
		}

		repository.deleteById(id);
	}
}