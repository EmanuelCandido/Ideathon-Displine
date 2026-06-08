package com.sistemaIdeathon.displine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaIdeathon.displine.entity.Professor;
// Interface de repositório responsável pelo acesso aos dados da entidade Professor no banco de dados
// Estende JpaRepository, herdando automaticamente operações como salvar, buscar, atualizar e deletar
// O primeiro parâmetro define a entidade gerenciada (Professor) e o segundo o tipo da chave primária (Long)
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
