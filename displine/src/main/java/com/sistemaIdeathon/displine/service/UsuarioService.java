package com.sistemaIdeathon.displine.service;

import java.time.LocalDate;
import java.util.List;

import com.sistemaIdeathon.displine.DTO.LoginRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaIdeathon.displine.DTO.UsuarioDTO;
import com.sistemaIdeathon.displine.DTO.UsuarioRequestDTO;
import com.sistemaIdeathon.displine.entity.Usuario;
import com.sistemaIdeathon.displine.Exception.RecursoNaoEncontradoException;
import com.sistemaIdeathon.displine.repository.UsuarioRepository;
// Define que essa classe é um serviço Spring, responsável pelas regras de negócio do Usuario
@Service
public class UsuarioService {
	// Injeta automaticamente o repositório responsável pelo acesso aos dados do Usuario
	@Autowired
	private UsuarioRepository repository;
	// Realiza a autenticação do usuário verificando e-mail e senha no banco de dados
	public UsuarioDTO login(LoginRequestDTO dto) {
		// Busca o usuário pelo e-mail e senha, lançando exceção caso as credenciais sejam inválidas
		Usuario usuario = repository.findByEmailAndSenha(dto.getEmail(), dto.getSenha())
				.orElseThrow(() -> new RuntimeException("E-mail ou senha inválidos"));

		return new UsuarioDTO(usuario);
	}
	// Busca todos os usuários no banco de dados e os converte para uma lista de UsuarioDTO
	public List<UsuarioDTO> listar() {
		return repository.findAll().stream().map(UsuarioDTO::new).toList();
	}
	// Cadastra um novo usuário no banco de dados a partir dos dados recebidos no DTO
	public UsuarioDTO salvar(UsuarioRequestDTO dto) {
		Usuario usuario = new Usuario();
		// Preenche os campos da entidade com os dados recebidos do cliente
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setTipoUsuario(dto.getTipoUsuario());
		// Define a data de cadastro como a data atual
		usuario.setDataCadastro(LocalDate.now());

		Usuario usuarioSalvo = repository.save(usuario);

		return new UsuarioDTO(usuarioSalvo);
	}
	// Atualiza os dados de um usuário existente identificado pelo id
	public UsuarioDTO atualizar(Long id, UsuarioRequestDTO dto) {
		// Busca o usuário pelo id, lançando exceção caso não seja encontrado
		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id));
		// Atualiza os campos da entidade com os novos dados recebidos do cliente
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setTipoUsuario(dto.getTipoUsuario());

		Usuario usuarioAtualizado = repository.save(usuario);

		return new UsuarioDTO(usuarioAtualizado);
	}
	// Remove um usuário do banco de dados pelo id
	public void deletar(Long id) {
		// Verifica se o usuário existe antes de tentar deletar, lançando exceção caso não seja encontrado
		if (!repository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id);
		}

		repository.deleteById(id);
	}
}