package com.sistemaIdeathon.displine.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Comandos pertencentes à biblioteca do Lombok, usada na automação de getters, setters e construtor vazio
@Getter
@Setter
@NoArgsConstructor
// Classe DTO de requisição usada para receber e validar os dados do Evento enviados pelo cliente
public class EventoRequestDTO {

    // Campo obrigatório — identificador do usuário ao qual o evento será associado
    @NotNull(message = "O usuário é obrigatório")
    private Long idUsuario;

    // Campo obrigatório — a data não pode ser anterior à data atual
    @NotNull(message = "A data do evento é obrigatória")
    @FutureOrPresent(message = "A data do evento não pode estar no passado")
    private LocalDate dataEvento;

    // Campo obrigatório — não pode ser vazio ou nulo
    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    // Descrição detalhada do evento — campo opcional
    private String descricao;

    // Campo obrigatório — horário de início do evento
    @NotNull(message = "O horário de início é obrigatório")
    private LocalTime horarioInicio;

    // Campo obrigatório — horário de término do evento
    @NotNull(message = "O horário de fim é obrigatório")
    private LocalTime horarioFim;

    // Campo obrigatório — tipo/categoria do evento (ex: prova, reunião, aula)
    @NotBlank(message = "O tipo do evento é obrigatório")
    private String tipoEvento;

    // Campo obrigatório — nível de prioridade do evento (ex: alta, média, baixa)
    @NotBlank(message = "A prioridade é obrigatória")
    private String prioridade;

    // Matéria relacionada ao evento — campo opcional
    private String materiaRelacionada;
}