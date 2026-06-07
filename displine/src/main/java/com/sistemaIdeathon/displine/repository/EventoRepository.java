package com.sistemaIdeathon.displine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaIdeathon.displine.entity.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByUsuarioId(Long idUsuario);
}