package com.sistemaIdeathon.displine.DTO;

import java.time.LocalDate;

import com.sistemaIdeathon.displine.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Comandos pertencentes à biblioteca do Lombok, usada na automação de getters, setters e construtor vazio
@Getter
@Setter
@NoArgsConstructor
// Classe DTO (Data Transfer Object) usada para trafegar os dados do Usuario entre as camadas da aplicação
public class UsuarioDTO {
    // Identificador único do usuário
    private Long id;

    // Nome completo do usuário
    private String nome;

    // Endereço de e-mail do usuário
    private String email;

    // Tipo do usuário no sistema (ex: aluno, professor)
    private String tipoUsuario;

    // Data em que o usuário foi cadastrado no sistema
    private LocalDate dataCadastro;

    // Construtor que converte uma entidade Usuario em UsuarioDTO,
    // mapeando cada campo da entidade para o respectivo atributo do DTO
    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.tipoUsuario = usuario.getTipoUsuario();
        this.dataCadastro = usuario.getDataCadastro();
    }

}