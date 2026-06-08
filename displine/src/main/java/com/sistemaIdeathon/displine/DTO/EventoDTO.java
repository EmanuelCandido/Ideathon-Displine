package com.sistemaIdeathon.displine.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import com.sistemaIdeathon.displine.entity.Evento;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Comandos pertencentes à biblioteca do Lombok, usada na automação de getters, setters e construtor vazio
@Getter
@Setter
@NoArgsConstructor
// Classe DTO (Data Transfer Object) usada para trafegar os dados do Evento entre as camadas da aplicação
public class EventoDTO {

	// Identificador único do evento
	private Long idEvento;

	// Identificador do usuário associado ao evento
	private Long idUsuario;

	// Data em que o evento ocorrerá
	private LocalDate dataEvento;

	// Título do evento
	private String titulo;

	// Descrição detalhada do evento
	private String descricao;

	// Horário de início do evento
	private LocalTime horarioInicio;

	// Horário de término do evento
	private LocalTime horarioFim;

	// Tipo/categoria do evento (ex: prova, reunião, aula)
	private String tipoEvento;

	// Nível de prioridade do evento (ex: alta, média, baixa)
	private String prioridade;

	// Matéria relacionada ao evento, quando aplicável
	private String materiaRelacionada;

	// Construtor que converte uma entidade Evento em EventoDTO,
	// mapeando cada campo da entidade para o respectivo atributo do DTO
	public EventoDTO(Evento evento) {
		this.idEvento = evento.getIdEvento();

		// Verifica se o usuário associado não é nulo antes de atribuir o id,
		// evitando NullPointerException
		if (evento.getUsuario() != null) {
			this.idUsuario = evento.getUsuario().getId();
		}

		this.dataEvento = evento.getDataEvento();
		this.titulo = evento.getTitulo();
		this.descricao = evento.getDescricao();
		this.horarioInicio = evento.getHorarioInicio();
		this.horarioFim = evento.getHorarioFim();
		this.tipoEvento = evento.getTipoEvento();
		this.prioridade = evento.getPrioridade();
		this.materiaRelacionada = evento.getMateriaRelacionada();
	}
}