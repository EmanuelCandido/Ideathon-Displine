package com.sistemaIdeathon.displine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaIdeathon.displine.entity.Usuario;
// Interface de repositório responsável pelo acesso aos dados da entidade Usuario no banco de dados
// Estende JpaRepository, herdando automaticamente operações como salvar, buscar, atualizar e deletar
// O primeiro parâmetro define a entidade gerenciada (Usuario) e o segundo o tipo da chave primária (Long)
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca um usuário pelo e-mail e senha combinados, utilizado no processo de autenticação/login
    // Retorna Optional para evitar NullPointerException caso o usuário não seja encontrado
    // O Spring Data JPA gera a query automaticamente com base no nome do método
    Optional<Usuario> findByEmailAndSenha(String email, String senha);
}