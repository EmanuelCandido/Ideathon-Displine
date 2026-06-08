package com.example.displine; // Declaração do pacote (diretório) onde este modelo de dados está localizado

// Importações da API java.time para manipulação moderna e nativa de datas e horários
import java.time.LocalDate; // Classe que representa uma data sem fuso horário (Ano-Mês-Dia)
import java.time.LocalTime; // Classe que representa um horário sem fuso horário (Hora:Minuto:Segundo)

// Definição da classe EventoItem, que serve como representação interna (Model) de um compromisso no aplicativo
public class EventoItem {

    // Atributos privados (encapsulados) que armazenam as propriedades específicas de cada compromisso
    private Long idEvento; // Código numérico identificador do evento
    private String titulo; // Nome ou título do compromisso
    private String descricao; // Texto detalhado com anotações sobre o evento
    private String tipoEvento; // Categoria do compromisso (ex: Aula, Prova, Trabalho)
    private String prioridade; // Nível de urgência (ex: Fácil, Médio, Alto, Urgente)
    private String materiaRelacionada; // Nome da disciplina/matéria vinculada
    private LocalDate dataEvento; // Instância de data para armazenar o dia do compromisso
    private LocalTime horarioInicio; // Instância de tempo para registrar a hora de início
    private LocalTime horarioFim; // Instância de tempo para registrar a hora de encerramento

    // Construtor padrão (vazio) sem parâmetros
    // Essencial para frameworks de serialização e para instanciar objetos vazios antes de preencher com os setters
    public EventoItem() {
    }

    // Construtor completo (sobrecarregado) que recebe todos os dados como parâmetros na criação do objeto
    public EventoItem(Long idEvento, String titulo, String descricao, String tipoEvento, String prioridade,
                      String materiaRelacionada, LocalDate dataEvento, LocalTime horarioInicio, LocalTime horarioFim) {
        // A palavra-chave 'this' resolve a ambiguidade, atribuindo o parâmetro recebido ao atributo da classe
        this.idEvento = idEvento;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipoEvento = tipoEvento;
        this.prioridade = prioridade;
        this.materiaRelacionada = materiaRelacionada;
        this.dataEvento = dataEvento;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
    }

    // MÉTODOS GETTERS E SETTERS
    // Permitem ler (get) e modificar (set) de forma segura os atributos que estão encapsulados como privados

    // Retorna o ID do evento
    public Long getIdEvento() {
        return idEvento;
    }

    // Define ou atualiza o ID do evento
    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    // Retorna o título do compromisso
    public String getTitulo() {
        return titulo;
    }

    // Define ou atualiza o título do compromisso
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Retorna a descrição do compromisso
    public String getDescricao() {
        return descricao;
    }

    // Define ou atualiza a descrição do compromisso
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Retorna a categoria/tipo do compromisso
    public String getTipoEvento() {
        return tipoEvento;
    }

    // Define ou atualiza a categoria/tipo do compromisso
    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    // Retorna a prioridade do compromisso
    public String getPrioridade() {
        return prioridade;
    }

    // Define ou atualiza a prioridade do compromisso
    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    // Retorna a matéria relacionada
    public String getMateriaRelacionada() {
        return materiaRelacionada;
    }

    // Define ou atualiza a matéria relacionada
    public void setMateriaRelacionada(String materiaRelacionada) {
        this.materiaRelacionada = materiaRelacionada;
    }

    // Retorna o objeto LocalDate contendo o dia do evento
    public LocalDate getDataEvento() {
        return dataEvento;
    }

    // Define ou atualiza o dia do evento usando a API LocalDate
    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    // Retorna o objeto LocalTime contendo a hora de início
    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    // Define ou atualiza a hora de início usando a API LocalTime
    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    // Retorna o objeto LocalTime contendo a hora de término
    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    // Define ou atualiza a hora de término usando a API LocalTime
    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }
}