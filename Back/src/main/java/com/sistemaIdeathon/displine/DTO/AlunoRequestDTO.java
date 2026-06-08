package com.sistemaIdeathon.displine.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Comandos pertencentes à biblioteca do Lombok, usada na automação de getters, setters e construtor vazio
@Getter
@Setter
@NoArgsConstructor
// Classe DTO de requisição usada para receber e validar os dados do Aluno enviados pelo cliente
public class AlunoRequestDTO {

    // Campo obrigatório — não pode ser vazio ou nulo
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    // Campo obrigatório e deve estar em formato de e-mail válido
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    // Campo obrigatório e deve ter no mínimo 4 caracteres
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 4, message = "A senha deve ter no mínimo 4 caracteres")
    private String senha;

    // Campo obrigatório — não pode ser vazio ou nulo
    @NotBlank(message = "A matrícula é obrigatória")
    private String matricula;

    // Campo obrigatório — não pode ser vazio ou nulo
    @NotBlank(message = "A turma é obrigatória")
    private String turma;

    // Campo obrigatório — não pode ser vazio ou nulo
    @NotBlank(message = "O curso é obrigatório")
    private String curso;
}