package com.sistemaIdeathon.displine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaIdeathon.displine.DTO.EventoDTO;
import com.sistemaIdeathon.displine.DTO.EventoRequestDTO;
import com.sistemaIdeathon.displine.entity.Evento;
import com.sistemaIdeathon.displine.entity.Usuario;
import com.sistemaIdeathon.displine.Exception.RecursoNaoEncontradoException;
import com.sistemaIdeathon.displine.repository.EventoRepository;
import com.sistemaIdeathon.displine.repository.UsuarioRepository;

// Define que essa classe é um serviço Spring, responsável pelas regras de negócio do Evento
@Service
public class EventoService {
    // Injeta automaticamente o repositório responsável pelo acesso aos dados do Evento
    @Autowired
    private EventoRepository repository;
    // Injeta automaticamente o repositório do Usuario, necessário para validar o usuário associado ao evento
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Busca todos os eventos no banco de dados e os converte para uma lista de EventoDT
    public List<EventoDTO> listar() {
        return repository.findAll().stream().map(EventoDTO::new).toList();
    }

    // Busca todos os eventos associados a um usuário específico pelo seu id
    public List<EventoDTO> listarPorUsuario(Long idUsuario) {
        return repository.findByUsuarioId(idUsuario)
                .stream()
                .map(EventoDTO::new)
                .toList();
    }
    // Cadastra um novo evento no banco de dados a partir dos dados recebidos no DTO
    public EventoDTO salvar(EventoRequestDTO dto) {
        // Busca o usuário pelo id informado no DTO, lançando exceção caso não seja encontrado
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + dto.getIdUsuario()));

        Evento evento = new Evento();
        // Preenche os campos da entidade com os dados recebidos do cliente
        evento.setUsuario(usuario);
        evento.setDataEvento(dto.getDataEvento());
        evento.setTitulo(dto.getTitulo());
        evento.setDescricao(dto.getDescricao());
        evento.setHorarioInicio(dto.getHorarioInicio());
        evento.setHorarioFim(dto.getHorarioFim());
        evento.setTipoEvento(dto.getTipoEvento());
        evento.setPrioridade(dto.getPrioridade());
        evento.setMateriaRelacionada(dto.getMateriaRelacionada());

        Evento eventoSalvo = repository.save(evento);

        return new EventoDTO(eventoSalvo);
    }
    // Atualiza os dados de um evento existente identificado pelo id
    public EventoDTO atualizar(Long id, EventoRequestDTO dto) {
        // Busca o evento pelo id, lançando exceção caso não seja encontrado
        Evento evento = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Evento não encontrado com id: " + id));
        // Busca o usuário pelo id informado no DTO, lançando exceção caso não seja encontrado
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + dto.getIdUsuario()));
        // Atualiza os campos da entidade com os novos dados recebidos do cliente
        evento.setUsuario(usuario);
        evento.setDataEvento(dto.getDataEvento());
        evento.setTitulo(dto.getTitulo());
        evento.setDescricao(dto.getDescricao());
        evento.setHorarioInicio(dto.getHorarioInicio());
        evento.setHorarioFim(dto.getHorarioFim());
        evento.setTipoEvento(dto.getTipoEvento());
        evento.setPrioridade(dto.getPrioridade());
        evento.setMateriaRelacionada(dto.getMateriaRelacionada());

        Evento eventoAtualizado = repository.save(evento);

        return new EventoDTO(eventoAtualizado);
    }
    // Remove um evento do banco de dados pelo id
    public void deletar(Long id) {
        // Verifica se o evento existe antes de tentar deletar, lançando exceção caso não seja encontrado
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Evento não encontrado com id: " + id);
        }

        repository.deleteById(id);
    }
}