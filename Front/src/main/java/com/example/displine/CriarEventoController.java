package com.example.displine; // Declaração do pacote (diretório) onde a classe do controlador está localizada

// Importações de componentes de interface, manipulação de eventos e gerenciamento de janelas do JavaFX
import javafx.event.ActionEvent; // Classe que gerencia os eventos de clique disparados pelos botões
import javafx.fxml.FXML; // Anotação obrigatória para conectar variáveis e métodos aos nós do arquivo FXML
import javafx.scene.control.*; // Importação em lote para todos os nós de controle (TextField, ComboBox, DatePicker, etc.)
import javafx.stage.Stage; // Classe que representa e manipula a janela física do sistema operacional

import java.time.LocalTime; // Classe do Java Time API usada para manipular horas, minutos e segundos (HH:mm)
import java.util.function.Consumer; // Interface funcional utilizada para encapsular e passar funções de retorno (callbacks)

public class CriarEventoController {

    // Componentes de entrada de dados mapeados a partir dos fxml:id declarados na interface gráfica
    @FXML private TextField txtTitulo; // Campo para o nome/título principal do compromisso
    @FXML private ComboBox<String> comboTipo; // Caixa de seleção suspensa para a categoria do compromisso
    @FXML private TextField txtMateria; // Campo para registrar a disciplina associada
    @FXML private DatePicker datePicker; // Componente gráfico de calendário para selecionar o dia
    @FXML private TextField txtInicio; // Campo de texto para preencher o horário inicial
    @FXML private TextField txtFim; // Campo de texto para preencher o horário final
    @FXML private TextArea txtDescricao; // Caixa de entrada de texto estendida para observações

    // Mapeamento dos botões para o controle de estados e alterações visuais em tempo de execução
    @FXML private Button btnFacil; // Botão de nível de prioridade "Fácil"
    @FXML private Button btnMedio; // Botão de nível de prioridade "Médio"
    @FXML private Button btnAlto; // Botão de nível de prioridade "Alto"
    @FXML private Button btnUrgente; // Botão de nível de prioridade "Urgente"
    @FXML private Button btnCancelar; // Botão de cancelamento para fechamento do diálogo

    // Atributos de controle de estado interno da classe controller
    private String prioridadeSelecionada = ""; // Armazena temporariamente a string da prioridade escolhida
    private EventoItem eventoEditando; // Armazena a referência do modelo caso a tela tenha sido aberta para edição
    private Consumer<EventoItem> aoSalvar; // Callback para transmitir o objeto EventoItem atualizado de volta à tela principal

    // Método executado automaticamente pelo JavaFX assim que o arquivo fxml correspondente termina de carregar
    @FXML
    public void initialize() {
        // Preenche as opções padrão do menu dropdown de tipos de compromisso
        comboTipo.getItems().addAll("Aula", "Prova", "Trabalho", "Estudo", "Outro");
    }

    // Método público que permite injetar a função de retorno a ser executada após o salvamento bem-sucedido
    public void setAoSalvar(Consumer<EventoItem> aoSalvar) {
        this.aoSalvar = aoSalvar;
    }

    // Método acionado externamente para preencher o formulário quando o usuário decide editar um compromisso existente
    public void carregarEvento(EventoItem evento) {
        this.eventoEditando = evento; // Salva o objeto original para rastrear que se trata de uma atualização, não de um novo registro

        // Distribui os dados do objeto recebido diretamente nos respectivos campos de texto e componentes visuais
        txtTitulo.setText(evento.getTitulo());
        comboTipo.setValue(evento.getTipoEvento());
        txtMateria.setText(evento.getMateriaRelacionada());
        datePicker.setValue(evento.getDataEvento());
        // Converte os valores temporais (LocalTime) de volta para String antes de embutir nos campos de preenchimento
        txtInicio.setText(String.valueOf(evento.getHorarioInicio()));
        txtFim.setText(String.valueOf(evento.getHorarioFim()));
        txtDescricao.setText(evento.getDescricao());

        // Recupera a prioridade salva no objeto e chama a rotina visual para deixar o botão correspondente realçado
        prioridadeSelecionada = evento.getPrioridade();
        marcarPrioridade(prioridadeSelecionada);
    }

    // Método executado quando o usuário clica em qualquer um dos quatro botões de prioridade
    @FXML
    void selecionarPrioridade(ActionEvent event) {
        // Captura o botão exato que disparou o clique através da propriedade getSource() do evento
        Button btnClicado = (Button) event.getSource();

        // Extrai o texto contido no rótulo do botão clicado (ex: "Fácil", "Médio") para definir o estado atual
        prioridadeSelecionada = btnClicado.getText();
        marcarPrioridade(prioridadeSelecionada); // Atualiza os realces de cores dos botões na interface gráfica
    }

    // Gerencia o visual dos quatro botões, desativando os estilos de seleção e destacando unicamente o correto
    private void marcarPrioridade(String prioridade) {
        // Reseta todos os quatro botões para o layout padrão de fábrica (fundo branco e borda cinza)
        resetarBotaoPrioridade(btnFacil);
        resetarBotaoPrioridade(btnMedio);
        resetarBotaoPrioridade(btnAlto);
        resetarBotaoPrioridade(btnUrgente);

        // Aplica cores em formato hexadecimal customizadas para o fundo, texto e borda dependendo do botão ativo
        if ("Fácil".equals(prioridade)) {
            destacar(btnFacil, "#EBF8FF", "#2B6CB0", "#BEE3F8"); // Paleta Azul
        } else if ("Médio".equals(prioridade)) {
            destacar(btnMedio, "#FEFCBF", "#975A16", "#FAF089"); // Paleta Amarela
        } else if ("Alto".equals(prioridade)) {
            destacar(btnAlto, "#FEEBC8", "#DD6B20", "#FBD38D"); // Paleta Laranja
        } else if ("Urgente".equals(prioridade)) {
            destacar(btnUrgente, "#FED7D7", "#C53030", "#FEB2B2"); // Paleta Vermelha
        }
    }

    // Função utilitária que injeta propriedades de estilo CSS inline no botão para criar o efeito visual de seleção
    private void destacar(Button btn, String fundo, String texto, String borda) {
        btn.setStyle("-fx-background-color: " + fundo + "; -fx-text-fill: " + texto + "; -fx-border-color: " + borda + "; -fx-border-radius: 6px; -fx-background-radius: 6px; -fx-font-weight: bold;");
    }

    // Função utilitária que redefine o CSS inline do botão passado para o visual padrão limpo
    private void resetarBotaoPrioridade(Button btn) {
        btn.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #4A5568; -fx-border-color: #CBD5E1; -fx-border-radius: 6px; -fx-background-radius: 6px;");
    }

    // Método acionado pelo botão "Salvar" que valida o formulário, cria/edita o modelo e sincroniza com a API
    @FXML
    void salvarEvento(ActionEvent event) {
        try {
            // Validação local preventiva: impede o envio caso campos essenciais estejam vazios ou sem seleção
            if (txtTitulo.getText().isBlank() || datePicker.getValue() == null || txtInicio.getText().isBlank()) {
                mostrarAlerta("Erro", "Preencha título, data e horário de início.");
                return; // Encerra o método imediatamente sem realizar a chamada de rede da API
            }

            EventoItem evento; // Declara a variável local que guardará o payload de dados

            // Se for nulo, significa que é um novo cadastro. Instancia uma nova entidade
            if (eventoEditando == null) {
                evento = new EventoItem();
            } else {
                // Caso contrário, reaproveita a entidade em edição mantendo o mesmo ID primário original
                evento = eventoEditando;
            }

            // Alimenta e atualiza as propriedades do objeto com as entradas da interface tratadas
            evento.setTitulo(txtTitulo.getText());
            evento.setTipoEvento(comboTipo.getValue());
            evento.setMateriaRelacionada(txtMateria.getText());
            evento.setDataEvento(datePicker.getValue());
            // Transforma a String digitada (ex: "14:00") em uma instância válida da classe LocalTime
            evento.setHorarioInicio(LocalTime.parse(txtInicio.getText()));
            // Operador ternário: se o horário de término estiver em branco, clona o horário de início como padrão de salvamento
            evento.setHorarioFim(txtFim.getText().isBlank() ? LocalTime.parse(txtInicio.getText()) : LocalTime.parse(txtFim.getText()));
            evento.setDescricao(txtDescricao.getText());
            // Fallback preventivo: se o usuário não escolheu uma prioridade, define automaticamente como "Fácil"
            evento.setPrioridade(prioridadeSelecionada.isBlank() ? "Fácil" : prioridadeSelecionada);

            // Constrói em tempo de execução um bloco de texto contendo o payload em formato de String JSON estruturada
            String json = """
                {
                    "idUsuario": %d,
                    "dataEvento": "%s",
                    "titulo": "%s",
                    "descricao": "%s",
                    "horarioInicio": "%s",
                    "horarioFim": "%s",
                    "tipoEvento": "%s",
                    "prioridade": "%s",
                    "materiaRelacionada": "%s"
                }
                """.formatted(
                    // Executa a substituição dos coringas %s e %d higienizando strings críticas via ApiClient.limpar()
                    SessaoUsuario.getIdUsuario(), // Resgata o ID numérico do usuário guardado na sessão estática global
                    evento.getDataEvento(),
                    ApiClient.limpar(evento.getTitulo()),
                    ApiClient.limpar(evento.getDescricao()),
                    evento.getHorarioInicio(),
                    evento.getHorarioFim(),
                    ApiClient.limpar(evento.getTipoEvento()),
                    ApiClient.limpar(evento.getPrioridade()),
                    ApiClient.limpar(evento.getMateriaRelacionada())
            );

            String resposta; // Declara a variável que receberá a String de retorno da API do backend

            // Tomada de decisão: escolhe a rota POST para criação ou a rota PUT para atualizar registros pré-existentes
            if (evento.getIdEvento() == null) {
                resposta = ApiClient.post("/Evento", json); // Envia o novo registro

                // Como é uma inserção, extrai manualmente da resposta textual o novo ID numérico gerado pelo banco
                Long idGerado = extrairIdEvento(resposta);
                evento.setIdEvento(idGerado); // Vincula o ID retornado ao objeto em memória
            } else {
                // Monta a URL parametrizada enviando o ID no caminho da rota para fazer o PUT de modificação
                resposta = ApiClient.put("/Evento/" + evento.getIdEvento(), json);
            }

            // Verifica se um callback de fechamento/atualização foi registrado no controlador
            if (aoSalvar != null) {
                aoSalvar.accept(evento); // Dispara a função passando o evento modificado para atualizar a tabela da listagem
            }

            // Exibe mensagem informativa de sucesso e aciona o fechamento da tela pop-up
            mostrarAlerta("Sucesso", "Compromisso salvo com sucesso!");
            fecharJanela();

        } catch (Exception e) {
            // Em caso de quebra de formato de hora, indisponibilidade de servidor ou erros negociais do backend
            mostrarErroFormatado("Erro ao salvar", e.getMessage());
            e.printStackTrace(); // Exibe a rastreabilidade completa do erro no console para depuração
        }
    }

    // Fecha a janela gráfica atual limpando-a do espaço de visualização do monitor
    @FXML
    void fecharJanela() {
        // Recupera o Stage (janela) atual escalando a árvore gráfica a partir do nó do botão cancelar
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close(); // Executa o encerramento físico da janela pop-up
    }

    // Constrói e exibe uma caixa modal informativa nativa para avisos padrão rápidos
    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Define o ícone de informação padrão azul
        alert.setTitle(titulo);
        alert.setHeaderText(null); // Remove o texto de cabeçalho cinza superior para simplificar o design
        alert.setContentText(mensagem);
        alert.showAndWait(); // Exibe a janela e interrompe a linha de execução até que o botão "OK" seja pressionado
    }

    // Analisador básico de texto (Parser rudimentar) para resgatar o ID gerado de dentro da String JSON de resposta do servidor
    private Long extrairIdEvento(String json) {
        try {
            String campo = "\"idEvento\":"; // Alvo de busca dentro da cadeia de caracteres
            int inicio = json.indexOf(campo); // Procura o índice numérico onde o campo de texto inicia

            if (inicio == -1) { // Se o índice retornar -1, significa que o atributo idEvento não consta no retorno do JSON
                return null;
            }

            inicio += campo.length(); // Desloca o ponteiro do cursor para pular os caracteres de '"idEvento":' e focar no valor numérico

            int fim = json.indexOf(",", inicio); // Procura a primeira vírgula após o valor do ID para delimitar o término

            if (fim == -1) { // Se não houver vírgula posterior, busca delimitar pelo fechamento de chaves do JSON
                fim = json.indexOf("}", inicio);
            }

            String valor = json.substring(inicio, fim).trim(); // Recorta o intervalo exato contendo o número e limpa espaços vazios

            return Long.parseLong(valor); // Converte a String de texto recortada de volta em um número longo (Long)

        } catch (Exception e) {
            return null; // Caso ocorra qualquer falha no fatiamento da String, retorna nulo defensivamente
        }
    }

    // Cria e exibe uma caixa modal de erro rica, contendo listas estilizadas e controle de dimensão
    private void mostrarErroFormatado(String titulo, String erroBruto) {
        String mensagemFormatada = formatarMensagemErro(erroBruto); // Traduz os termos da API em frases humanizadas

        Alert alert = new Alert(Alert.AlertType.ERROR); // Define o ícone padrão vermelho com sinalização de perigo
        alert.setTitle(titulo);
        alert.setHeaderText("Não foi possível salvar o compromisso");

        Label label = new Label(mensagemFormatada); // Constrói o rótulo de texto em tempo de execução
        label.setWrapText(true); // Permite que o texto mude de linha sozinho caso estoure o limite de tamanho do modal
        label.setMaxWidth(400); // Trava a dimensão máxima do componente textual

        alert.getDialogPane().setContent(label); // Injeta o nó do label diretamente no painel customizável de conteúdo do diálogo
        alert.getDialogPane().setPrefWidth(470); // Define a largura preferencial do diálogo de erro na tela

        alert.showAndWait(); // Trava a janela de erro na tela aguardando a dispensa do usuário
    }

    // Analisa as mensagens técnicas brutas geradas pela validação e as transforma em marcadores descritivos legíveis
    private String formatarMensagemErro(String erroBruto) {
        // Validação defensiva básica: se não houver mensagem, retorna uma frase padrão genérica de falha
        if (erroBruto == null || erroBruto.isBlank()) {
            return "Ocorreu um erro inesperado.";
        }

        // Instancia um StringBuilder para agrupar e acumular de forma eficiente a lista final de erros encontrados
        StringBuilder sb = new StringBuilder();

        // Estruturas de decisão que verificam a existência de palavras-chave do banco/API na string de erro original
        if (erroBruto.contains("dataEvento")) {
            sb.append("• A data do compromisso é obrigatória ou não pode estar no passado.\n");
        }

        if (erroBruto.contains("titulo")) {
            sb.append("• O título é obrigatório.\n");
        }

        if (erroBruto.contains("horarioInicio")) {
            sb.append("• O horário de início é obrigatório.\n");
        }

        if (erroBruto.contains("horarioFim")) {
            sb.append("• O horário de fim é obrigatório.\n");
        }

        if (erroBruto.contains("tipoEvento")) {
            sb.append("• O tipo do compromisso é obrigatório.\n");
        }

        if (erroBruto.contains("prioridade")) {
            sb.append("• A prioridade é obrigatória.\n");
        }

        if (erroBruto.contains("idUsuario")) {
            sb.append("• O usuário logado não foi identificado. Faça login novamente.\n");
        }

        // Se o StringBuilder continuar vazio, indica que o erro não foi mapeado pelas condições de palavras-chave
        if (sb.isEmpty()) {
            return "Verifique os dados preenchidos e tente novamente."; // Retorna uma instrução padrão genérica
        }

        return sb.toString(); // Converte o agrupador StringBuilder em texto comum para preenchimento final do alerta
    }
}