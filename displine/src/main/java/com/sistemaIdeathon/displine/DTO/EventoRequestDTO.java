package com.sistemaIdeathon.displine.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventoRequestDTO {

    @NotNull(message = "O usuário é obrigatório")
    private Long idUsuario;

    @NotNull(message = "A data do evento é obrigatória")
    @FutureOrPresent(message = "A data do evento não pode estar no passado")
    private LocalDate dataEvento;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    private String descricao;

    @NotNull(message = "O horário de início é obrigatório")
    private LocalTime horarioInicio;

    @NotNull(message = "O horário de fim é obrigatório")
    private LocalTime horarioFim;

    @NotBlank(message = "O tipo do evento é obrigatório")
    private String tipoEvento;

    @NotBlank(message = "A prioridade é obrigatória")
    private String prioridade;

    private String materiaRelacionada;
}