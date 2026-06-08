package com.example.displine; // Declaração do pacote (diretório) onde este arquivo de transferência de dados está localizado

// Definição da classe EventoApiDTO (Data Transfer Object - Objeto de Transferência de Dados)
// Essa classe serve exclusivamente para espelhar a estrutura do JSON que trafega entre o backend (API) e o frontend
public class EventoApiDTO {

    // Atributos privados (encapsulados) que representam os campos de dados de um evento/compromisso
    private Long idEvento; // Identificador único do evento gerado no banco de dados
    private Long idUsuario; // Identificador único do usuário dono deste compromisso
    private String dataEvento; // Data do compromisso armazenada temporariamente como texto (String)
    private String titulo; // Nome ou título resumido do compromisso
    private String descricao; // Detalhes, anotações ou observações textuais do evento
    private String horarioInicio; // Hora de início guardada temporariamente como texto
    private String horarioFim; // Hora de término guardada temporariamente como texto
    private String tipoEvento; // Categoria do compromisso (ex: Aula, Prova, Trabalho)
    private String prioridade; // Nível de importância (ex: Fácil, Médio, Alto, Urgente)
    private String materiaRelacionada; // Nome da disciplina escolar ou acadêmica vinculada

    // MÉTODOS GETTERS PÚBLICOS
    // Fornecem acesso de leitura controlada aos atributos privados de fora desta classe

    // Retorna o ID único do evento
    public Long getIdEvento() {
        return idEvento;
    }

    // Retorna o ID do usuário associado
    public Long getIdUsuario() {
        return idUsuario;
    }

    // Retorna a data cadastrada para o evento
    public String getDataEvento() {
        return dataEvento;
    }

    // Retorna o título do compromisso
    public String getTitulo() {
        return titulo;
    }

    // Retorna a descrição ou observação do evento
    public String getDescricao() {
        return descricao;
    }

    // Retorna a representação textual do horário de início
    public String getHorarioInicio() {
        return horarioInicio;
    }

    // Retorna a representação textual do horário de término
    public String getHorarioFim() {
        return horarioFim;
    }

    // Retorna a categoria/tipo do compromisso
    public String getTipoEvento() {
        return tipoEvento;
    }

    // Retorna o nível de prioridade definido
    public String getPrioridade() {
        return prioridade;
    }

    // Retorna o nome da matéria vinculada ao evento
    public String getMateriaRelacionada() {
        return materiaRelacionada;
    }
}