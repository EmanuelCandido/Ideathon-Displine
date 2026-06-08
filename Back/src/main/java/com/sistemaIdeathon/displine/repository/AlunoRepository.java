package com.sistemaIdeathon.displine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaIdeathon.displine.entity.Aluno;
// Interface de repositório responsável pelo acesso aos dados da entidade Aluno no banco de dados
// Estende JpaRepository, herdando automaticamente operações como salvar, buscar, atualizar e deletar
// O primeiro parâmetro define a entidade gerenciada (Aluno) e o segundo o tipo da chave primária (Long)
public interface AlunoRepository extends JpaRepository<Aluno,Long>{

	
	
	
	
}
