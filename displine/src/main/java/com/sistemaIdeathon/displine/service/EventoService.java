package com.sistemaIdeathon.displine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaIdeathon.displine.DTO.EventoDTO;
import com.sistemaIdeathon.displine.entity.Evento;
import com.sistemaIdeathon.displine.repository.EventoRepository;

@Service
public class EventoService {

	@Autowired
    private EventoRepository repository;
	
	public List<EventoDTO> listar() {
        return repository.findAll().stream().map(EventoDTO::new).toList();
    }

	public Evento salvar(Evento evento) {
	        return repository.save(evento);
	}

	public void deletar(Long id) {
        repository.deleteById(id);
    }

	public Evento atualizar(Long id, Evento evento) {

        evento.setIdEvento(id);
        
        return repository.save(evento);
    }
	
	
	
}
