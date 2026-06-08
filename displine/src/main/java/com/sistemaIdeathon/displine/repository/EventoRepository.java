package com.sistemaIdeathon.displine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaIdeathon.displine.entity.Evento;

// Interface de repositório responsável pelo acesso aos dados da entidade Evento no banco de dados
// Estende JpaRepository, herdando automaticamente operações como salvar, buscar, atualizar e deletar
// O primeiro parâmetro define a entidade gerenciada (Evento) e o segundo o tipo da chave primária (Long)
public interface EventoRepository extends JpaRepository<Evento, Long> {
    // Busca todos os eventos associados a um usuário específico pelo seu id
    // O Spring Data JPA gera a query automaticamente com base no nome do método
    List<Evento> findByUsuarioId(Long idUsuario);
}