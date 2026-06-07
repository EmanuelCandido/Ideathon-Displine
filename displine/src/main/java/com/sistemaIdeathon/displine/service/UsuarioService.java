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

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public UsuarioDTO login(LoginRequestDTO dto) {
		Usuario usuario = repository.findByEmailAndSenha(dto.getEmail(), dto.getSenha())
				.orElseThrow(() -> new RuntimeException("E-mail ou senha inválidos"));

		return new UsuarioDTO(usuario);
	}

	public List<UsuarioDTO> listar() {
		return repository.findAll().stream().map(UsuarioDTO::new).toList();
	}

	public UsuarioDTO salvar(UsuarioRequestDTO dto) {
		Usuario usuario = new Usuario();

		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setTipoUsuario(dto.getTipoUsuario());
		usuario.setDataCadastro(LocalDate.now());

		Usuario usuarioSalvo = repository.save(usuario);

		return new UsuarioDTO(usuarioSalvo);
	}

	public UsuarioDTO atualizar(Long id, UsuarioRequestDTO dto) {
		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id));

		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setTipoUsuario(dto.getTipoUsuario());

		Usuario usuarioAtualizado = repository.save(usuario);

		return new UsuarioDTO(usuarioAtualizado);
	}

	public void deletar(Long id) {
		if (!repository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id);
		}

		repository.deleteById(id);
	}
}