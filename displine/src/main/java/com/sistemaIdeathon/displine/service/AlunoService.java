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

// Define que essa classe é um serviço Spring, responsável pelas regras de negócio do Aluno
@Service
public class AlunoService {
	// Injeta automaticamente o repositório responsável pelo acesso aos dados do Aluno
	@Autowired
	private AlunoRepository repository;
	// Busca todos os alunos no banco de dados e os converte para uma lista de AlunoDTO
	public List<AlunoDTO> listar() {
		return repository.findAll().stream().map(AlunoDTO::new).toList();
	}

	// Cadastra um novo aluno no banco de dados a partir dos dados recebidos no DTO
	public AlunoDTO salvar(AlunoRequestDTO dto) {
		Aluno aluno = new Aluno();
		// Preenche os campos da entidade com os dados recebidos do cliente
		aluno.setNome(dto.getNome());
		aluno.setEmail(dto.getEmail());
		aluno.setSenha(dto.getSenha());
		aluno.setMatricula(dto.getMatricula());
		aluno.setTurma(dto.getTurma());
		aluno.setCurso(dto.getCurso());
		// Define o tipo do usuário fixamente como "Aluno"
		aluno.setTipoUsuario("Aluno");
		// Define a data de cadastro como a data atual
		aluno.setDataCadastro(LocalDate.now());

		Aluno alunoSalvo = repository.save(aluno);

		return new AlunoDTO(alunoSalvo);
	}
	// Atualiza os dados de um aluno existente identificado pelo id
	public AlunoDTO atualizar(Long id, AlunoRequestDTO dto) {
		// Busca o aluno pelo id, lançando exceção caso não seja encontrado
		Aluno aluno = repository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado com id: " + id));
		// Atualiza os campos da entidade com os novos dados recebidos do cliente
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
	// Remove um aluno do banco de dados pelo id
	public void deletar(Long id) {
		// Verifica se o aluno existe antes de tentar deletar, lançando exceção caso não seja encontrado
		if (!repository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Aluno não encontrado com id: " + id);
		}

		repository.deleteById(id);
	}
}