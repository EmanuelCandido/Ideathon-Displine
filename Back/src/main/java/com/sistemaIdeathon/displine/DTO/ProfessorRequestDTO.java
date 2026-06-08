package com.sistemaIdeathon.displine.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Comandos pertencentes à biblioteca do Lombok, usada na automação de getters, setters e construtor vazio
@Getter
@Setter
@NoArgsConstructor
// Classe DTO de requisição usada para receber e validar os dados do Professor enviados pelo cliente
public class ProfessorRequestDTO {

    // Campo obrigatório — não pode ser vazio ou nulo
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    // Campo obrigatório e deve estar em formato de e-mail válido
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    // Campo obrigatório — não pode ser vazio ou nulo
    @NotBlank(message = "A matéria é obrigatória")
    private String materia;

    // Campo obrigatório — não pode ser vazio ou nulo
    @NotBlank(message = "A turma é obrigatória")
    private String turma;
}