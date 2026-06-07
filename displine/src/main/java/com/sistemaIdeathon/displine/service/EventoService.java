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

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<EventoDTO> listar() {
        return repository.findAll().stream().map(EventoDTO::new).toList();
    }

    public List<EventoDTO> listarPorUsuario(Long idUsuario) {
        return repository.findByUsuarioId(idUsuario)
                .stream()
                .map(EventoDTO::new)
                .toList();
    }

    public EventoDTO salvar(EventoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + dto.getIdUsuario()));

        Evento evento = new Evento();

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

    public EventoDTO atualizar(Long id, EventoRequestDTO dto) {
        Evento evento = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Evento não encontrado com id: " + id));

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + dto.getIdUsuario()));

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

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Evento não encontrado com id: " + id);
        }

        repository.deleteById(id);
    }
}