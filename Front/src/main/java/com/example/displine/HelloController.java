package com.example.displine; // Declaração do pacote onde o controlador principal está localizado

// Importações do JavaFX para gerenciamento do layout de tela, injeção de nós e tratamento de ações do usuário
import javafx.fxml.FXML; // Anotação obrigatória para vincular os atributos e métodos ao arquivo principal .fxml
import javafx.fxml.FXMLLoader; // Carregador usado para inflar e instanciar os layouts XML em nós gráficos
import javafx.geometry.Pos; // Enumeração de posicionamento para alinhar os componentes nas células (ex: CENTER)
import javafx.scene.Scene; // Container que gerencia a árvore visual de elementos carregada em uma janela
import javafx.scene.control.Button; // Controle de botão padrão clicável
import javafx.scene.control.Label; // Controle de texto simples não editável usado para rótulos e mensagens
import javafx.scene.layout.GridPane; // Layout em formato de matriz/tabela estruturado em linhas e colunas
import javafx.scene.layout.VBox; // Container que agrupa e empilha elementos de forma vertical
import javafx.stage.Modality; // Enum que controla o comportamento modal (bloqueante) de novas subjanelas
import javafx.stage.Stage; // Classe que manipula o ciclo de vida de uma janela física no sistema

// Importações utilitárias nativas do Java para manipulação de coleções, tempo, fluxos e localização
import java.io.IOException; // Exceção disparada caso ocorra algum erro na leitura do arquivo .fxml
import java.time.LocalDate; // Representa uma data específica (Ano-Mês-Dia) sem informações de fuso horário
import java.time.YearMonth; // Tipo de dado otimizado para lidar com a combinação de um Ano e um Mês específicos
import java.time.format.DateTimeFormatter; // Formatador e conversor textual para instâncias temporais do Java
import java.time.format.TextStyle; // Configura o tamanho de exibição do nome de meses ou dias (FULL, SHORT, etc.)
import java.util.ArrayList; // Implementação de lista dinâmica baseada em vetor redimensionável
import java.util.Comparator; // Interface para criar regras de comparação e ordenação customizada de coleções
import java.util.List; // Interface de coleção ordenada (List) do ecossistema do Java Collections
import java.util.Locale; // Objeto que define a localidade geográfica (Idioma e País) para fins de formatação

public class HelloController {

    // Vínculos FXML para os componentes de exibição do calendário
    @FXML private GridPane gridCalendario; // A grade visual onde serão desenhados os dias do mês
    @FXML private Label lblMesAno; // Texto centralizado que exibe o nome do mês e ano vigentes

    // Vínculos FXML para elementos informativos e seções do painel lateral/superior
    @FXML private Label lblSaudacao; // Rótulo de texto usado para saudar o usuário logado
    @FXML private VBox boxDashboard; // Container raiz da seção do Painel Principal
    @FXML private VBox boxForum; // Container raiz da seção do Fórum
    @FXML private VBox boxProximos; // Caixa lateral que lista os compromissos mais urgentes
    @FXML private VBox boxTodosCompromissos; // Container raiz da tela de listagem geral
    @FXML private VBox boxListaTodosCompromissos; // Caixa interna onde serão embutidos os cards de todos os compromissos

    // Vínculos FXML para os botões de navegação do menu principal
    @FXML private Button btnDashboard; // Botão de alternância para o Dashboard
    @FXML private Button btnForum; // Botão de alternância para o Fórum

    // Variáveis de controle de estado interno da aplicação
    private YearMonth mesAtual; // Armazena a referência do mês/ano que está sendo visualizado na tela atual
    private final List<EventoItem> eventos = new ArrayList<>(); // Lista em memória que armazena os compromissos do usuário

    // Método de inicialização acionado automaticamente pelo JavaFX assim que o arquivo FXML base é carregado
    @FXML
    public void initialize() {
        mesAtual = YearMonth.now(); // Captura o mês e o ano vigentes do relógio do sistema operacional

        // Verifica se existe um nome válido registrado na Sessão estática global para saudar o usuário
        if (SessaoUsuario.getNome() != null && !SessaoUsuario.getNome().isBlank()) {
            lblSaudacao.setText("Olá, " + SessaoUsuario.getNome() + "!"); // Personaliza a saudação com o nome cadastrado
        } else {
            lblSaudacao.setText("Olá!"); // Caso não identifique o nome, aplica uma saudação simplificada padrão
        }

        carregarEventosDoUsuario(); // Executa a requisição de rede para buscar os compromissos armazenados no backend

        // Executa as rotinas sequenciais de preenchimento e visibilidade dos componentes da interface gráfica
        mostrarDashboard(); // Configura a tela inicial mostrando a aba do painel e ocultando as demais
        montarCalendario(); // Constrói dinamicamente a matriz de dias e preenche as datas na grade do calendário
        montarProximosCompromissos(); // Filtra e preenche a barra lateral com as 5 tarefas mais recentes
        montarTodosCompromissos(); // Prepara a massa de dados para preenchimento da aba de listagem geral
        montarForum(); // Desenha a estrutura padrão vazia da seção da comunidade
    }

    // Método privado encarregado de calcular, desenhar e preencher a matriz do calendário do mês atual
    private void montarCalendario() {
        gridCalendario.getChildren().clear(); // Limpa completamente os nós gráficos residuais remanescentes do calendário anterior
        atualizarTituloMesAno(); // Modifica o cabeçalho de texto da tela para exibir o mês vigente em português

        // Vetor contendo as abreviações dos dias da semana que servirão de cabeçalho para a grade do calendário
        String[] diasDaSemana = {"DOM", "SEG", "TER", "QUA", "QUI", "SEX", "SÁB"};

        // Loop for para criar e encaixar as células de rótulo dos dias da semana na linha zero (0) do GridPane
        for (int i = 0; i < diasDaSemana.length; i++) {
            Label labelDia = new Label(diasDaSemana[i]); // Instancia o rótulo com o nome da abreviação
            labelDia.setStyle("-fx-font-weight: bold; -fx-text-fill: #718096; -fx-font-size: 11px;"); // Estilização em CSS

            VBox header = new VBox(labelDia); // Cria um mini container vertical para envelopar o texto do dia
            header.setAlignment(Pos.CENTER); // Centraliza o texto horizontal e verticalmente dentro deste container
            header.setStyle("-fx-padding: 10; -fx-border-color: #EDF2F7; -fx-border-width: 0.5;"); // Aplica bordas leves de grade
            gridCalendario.add(header, i, 0); // Insere o container na coluna 'i', na linha fixa '0' do GridPane
        }

        LocalDate primeiroDia = mesAtual.atDay(1); // Determina a data exata do primeiro dia (dia 1) do mês em questão
        // Calcula em qual coluna da semana o mês inicia usando resto da divisão para adequar o domingo à coluna 0
        int coluna = primeiroDia.getDayOfWeek().getValue() % 7;
        int row = 1; // Inicializa a linha do calendário como 1, logo abaixo do cabeçalho dos dias da semana

        // Loop principal que percorre cada dia do mês atual (ex: de 1 até 30 ou 31)
        for (int dia = 1; dia <= mesAtual.lengthOfMonth(); dia++) {
            LocalDate data = mesAtual.atDay(dia); // Cria o objeto LocalDate correspondente ao número do dia atual do loop

            Label lblNumero = new Label(String.valueOf(dia)); // Cria o rótulo de texto que exibe o número do dia da folhinha
            lblNumero.setStyle("-fx-font-size: 12px; -fx-text-fill: #2D3748;"); // Aplica o design do número do dia

            VBox celula = new VBox(5); // Instancia a caixinha vertical da célula da data com 5 pixels de espaçamento interno
            celula.getChildren().add(lblNumero); // Anexa o número do dia no topo interno desta caixinha

            // Verifica se a data que está sendo desenhada corresponde exatamente ao dia de hoje (data atual do sistema)
            if (data.equals(LocalDate.now())) {
                // Aplica um estilo diferenciado com fundo azul claro e bordas grossas para destacar o dia de hoje
                celula.setStyle(
                        "-fx-border-color: #0056C6;" +
                                "-fx-border-width: 2;" +
                                "-fx-padding: 8;" +
                                "-fx-min-width: 95;" +
                                "-fx-min-height: 72;" +
                                "-fx-background-color: #EBF8FF;"
                );

                // Aplica formatação destacada em negrito e azul escuro para o número do dia atual
                lblNumero.setStyle(
                        "-fx-font-size: 12px;" +
                                "-fx-text-fill: #0056C6;" +
                                "-fx-font-weight: bold;"
                );
            } else {
                // Caso contrário, aplica o layout minimalista padrão com bordas finas para as demais datas do mês
                celula.setStyle(
                        "-fx-border-color: #EDF2F7;" +
                                "-fx-border-width: 0.5;" +
                                "-fx-padding: 8;" +
                                "-fx-min-width: 95;" +
                                "-fx-min-height: 72;"
                );
            }

            // Varre a lista completa de compromissos salvos do usuário procurando eventos que coincidam com esta data
            for (EventoItem evento : eventos) {
                if (evento.getDataEvento().equals(data)) {
                    // Instancia um rótulo de texto exibindo o horário de início e o título do compromisso encontrado
                    Label lblEvento = new Label(evento.getHorarioInicio() + " " + evento.getTitulo());
                    // Estiliza a etiqueta do evento com fundo azul, bordas arredondadas e muda o ponteiro do mouse para indicar clique
                    lblEvento.setStyle("-fx-background-color: #EBF8FF; -fx-text-fill: #2B6CB0; -fx-font-size: 9px; -fx-padding: 3; -fx-background-radius: 3; -fx-cursor: hand;");
                    // Associa o clique do mouse na etiqueta para abrir o modal de edição carregando as informações do evento clicado
                    lblEvento.setOnMouseClicked(e -> abrirModalEditarEvento(evento));
                    celula.getChildren().add(lblEvento); // Adiciona a etiqueta do evento para exibição dentro do bloco do dia
                }
            }

            gridCalendario.add(celula, coluna, row); // Insere o container completo do dia na coordenada calculada da matriz

            coluna++; // Avança para a coluna do próximo dia da semana
            if (coluna > 6) { // Se ultrapassar a coluna do sábado (índice 6)
                coluna = 0; // Reinicia a contagem das colunas voltando para o domingo (índice 0)
                row++; // Quebra a linha avançando para o próximo nível horizontal da matriz
            }
        }
    }

    // Filtra e renderiza os próximos 5 compromissos futuros ou presentes na área lateral do painel
    private void montarProximosCompromissos() {
        boxProximos.getChildren().clear(); // Limpa os nós antigos contidos na caixinha lateral de próximos compromissos

        eventos.stream() // Inicia o processamento da lista de eventos utilizando a API de Streams do Java
                // Filtra os eventos descartando os que possuem data anterior ao dia de hoje (remove os antigos)
                .filter(e -> !e.getDataEvento().isBefore(LocalDate.now()))
                // Ordena os resultados de forma crescente por data do evento e, em caso de empate, pelo horário de início
                .sorted(Comparator.comparing(EventoItem::getDataEvento).thenComparing(EventoItem::getHorarioInicio))
                .limit(5) // Corta o fluxo limitando a exibição para capturar no máximo os 5 primeiros compromissos da fila
                // Para cada evento resultante, constrói um card e injeta o nó gráfico como filho na caixa vertical lateral
                .forEach(evento -> boxProximos.getChildren().add(criarCardCompromisso(evento)));

        // Se após o processamento a caixa continuar vazia, exibe uma mensagem textual amigável ao usuário
        if (boxProximos.getChildren().isEmpty()) {
            Label vazio = new Label("Nenhum compromisso cadastrado."); // Cria o rótulo de ausência de registros
            vazio.setStyle("-fx-text-fill: #718096; -fx-font-size: 13px;"); // Estilização cinza neutra
            boxProximos.getChildren().add(vazio); // Fixa o aviso visual na tela lateral
        }
    }

    // Desenha e preenche de forma exaustiva a listagem de todos os compromissos cadastrados na aba dedicada
    private void montarTodosCompromissos() {
        boxListaTodosCompromissos.getChildren().clear(); // Limpa as referências antigas da listagem geral

        eventos.stream() // Inicia a stream de processamento sobre a coleção total de eventos em memória
                // Ordena cronologicamente todos os eventos por ordem de data e posteriormente por hora de início
                .sorted(Comparator.comparing(EventoItem::getDataEvento).thenComparing(EventoItem::getHorarioInicio))
                // Percorre todos os itens estruturando e inserindo o card correspondente dentro do container vertical da lista
                .forEach(evento -> boxListaTodosCompromissos.getChildren().add(criarCardCompromisso(evento)));

        // Verifica se a lista geral está vazia para emitir um feedback textual indicativo
        if (boxListaTodosCompromissos.getChildren().isEmpty()) {
            Label vazio = new Label("Nenhum compromisso cadastrado."); // Rótulo indicador de lista limpa
            vazio.setStyle("-fx-text-fill: #718096; -fx-font-size: 13px;"); // Estilo padrão cinza
            boxListaTodosCompromissos.getChildren().add(vazio); // Adiciona o aviso textual na tela
        }
    }

    // Método utilitário responsável por fabricar em tempo de execução um componente visual VBox estruturado como "card" de compromisso
    private VBox criarCardCompromisso(EventoItem evento) {
        VBox card = new VBox(4); // Cria um painel vertical com 4 pixels de folga interna entre seus nós internos

        // Resguarda a string de prioridade tratando possíveis valores nulos preventivamente como string vazia
        String prioridade = evento.getPrioridade() == null ? "" : evento.getPrioridade();

        // Estrutura de escolha chaveada (Switch Expression) que retorna uma cor hexadecimal diferente baseada no grau de risco/prioridade
        String corBorda = switch (prioridade) {
            case "Urgente" -> "#E53E3E"; // Vermelho forte
            case "Alto" -> "#DD6B20"; // Laranja
            case "Médio" -> "#D69E2E"; // Amarelo/Dourado
            default -> "#38A169"; // Verde padrão para tarefas com classificação "Fácil" ou não preenchidas
        };

        // Aplica o design do card, configurando um fundo acinzentado claro, cantos arredondados, margens internas
        // e cria uma borda vertical colorida espessa apenas na extremidade esquerda (borda de prioridade: 0 0 0 4)
        card.setStyle(
                "-fx-background-color: #F7FAFC;" +
                        "-fx-padding: 12;" +
                        "-fx-background-radius: 6;" +
                        "-fx-border-color: " + corBorda + ";" +
                        "-fx-border-width: 0 0 0 4;" +
                        "-fx-cursor: hand;"
        );

        // Instancia o rótulo do título do compromisso em negrito com tom escuro e tamanho destacado de 14px
        Label titulo = new Label(evento.getTitulo());
        titulo.setStyle("-fx-font-weight: bold; -fx-text-fill: #1A202C; -fx-font-size: 14px;");

        // Cria a linha informativa combinando ícones unicode de calendário/relógio e injeta a data formatada no padrão brasileiro
        Label info = new Label("📅 " + evento.getDataEvento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                "  •  ⏱ " + evento.getHorarioInicio());
        info.setStyle("-fx-text-fill: #718096; -fx-font-size: 12px;"); // Estilo em tamanho reduzido cinza

        Label descricao = new Label(); // Instancia o componente que receberá o bloco longo da descrição da tarefa

        // Verifica a presença de conteúdo textual na descrição para evitar impressões nulas na tela do usuário
        if (evento.getDescricao() != null && !evento.getDescricao().isBlank()) {
            descricao.setText(evento.getDescricao()); // Adiciona a descrição real do objeto
        } else {
            descricao.setText("Sem descrição."); // Exibe o texto alternativo padrão caso esteja vazio
        }

        descricao.setWrapText(true); // Ativa o recurso de quebra de linha por palavra automática se o texto esticar demais
        descricao.setMaxWidth(600); // Trava a largura limite do rótulo textual para prevenir distorções na interface gráfica
        descricao.setStyle("-fx-text-fill: #4A5568; -fx-font-size: 12px;"); // Configura a paleta de cor do texto descritivo

        card.getChildren().addAll(titulo, info, descricao); // Incorpora em lote o título, a linha de info e a descrição ao card
        card.setOnMouseClicked(e -> abrirModalEditarEvento(evento)); // Associa o clique físico do mouse no card para abrir edição do registro

        return card; // Retorna o componente gráfico estruturado e pronto para montagem nas listagens
    }

    // Configura e popula estaticamente os textos da seção visual simulada do Fórum
    private void montarForum() {
        boxForum.getChildren().clear(); // Limpa as referências internas da caixa do fórum

        // Instancia e estiliza o título da tela do fórum da comunidade
        Label titulo = new Label("Fórum da Comunidade");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #1A202C;");

        VBox card = new VBox(8); // Cria um contêiner vertical interno com margem interna de 8 pixels
        // Estiliza o bloco simulador do fórum com fundo branco, preenchimento interno de 18 pixels e bordas arredondadas leves
        card.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 18; -fx-background-radius: 8; -fx-border-color: #E2E8F0; -fx-border-radius: 8;");

        // Rótulo interno simulando um indicador de cabeçalho de discussões vazio
        Label topico = new Label("Nenhuma discussão cadastrada.");
        topico.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #1A202C;");

        // Rótulo textual com uma mensagem de suporte informativa complementar
        Label texto = new Label("As dúvidas e discussões da comunidade aparecerão aqui.");
        texto.setStyle("-fx-font-size: 13px; -fx-text-fill: #718096;");

        card.getChildren().addAll(topico, texto); // Anexa as duas mensagens de texto no card interno branco
        boxForum.getChildren().addAll(titulo, card); // Vincula o título geral e o card ao container principal do fórum
    }

    // Formata o título superior do calendário transformando a primeira letra do mês em maiúscula junto ao ano correspondente
    private void atualizarTituloMesAno() {
        // Resgata o nome por extenso do mês atual traduzido na localidade brasileira (pt-BR)
        String nomeMes = mesAtual.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
        // Executa uma fatia e capitalização da String para deixar a primeira letra do mês obrigatoriamente em maiúscula
        nomeMes = nomeMes.substring(0, 1).toUpperCase() + nomeMes.substring(1);
        lblMesAno.setText(nomeMes + " " + mesAtual.getYear()); // Atualiza o rótulo visual concatenando o Mês Capitalizado e o Ano
    }

    // Retrocede o estado do calendário em exatamente 1 mês ao clicar no botão de seta para a esquerda
    @FXML
    void mesAnterior() {
        mesAtual = mesAtual.minusMonths(1); // Deduz um mês da referência temporal controlada da classe
        montarCalendario(); // Redesenha toda a estrutura e eventos da grade com base na nova data calculada
    }

    // Avança o estado do calendário em exatamente 1 mês ao clicar no botão de seta para a direita
    @FXML
    void proximoMes() {
        mesAtual = mesAtual.plusMonths(1); // Incrementa um mês na referência temporal controlada da classe
        montarCalendario(); // Redesenha toda a estrutura e eventos da grade com base na nova data calculada
    }

    // Método de navegação que ativa a visibilidade da aba do Painel Geral (Dashboard) e reconfigura o estilo dos botões
    @FXML
    void mostrarDashboard() {
        boxDashboard.setVisible(true); // Exibe visualmente o bloco de componentes do Dashboard
        boxDashboard.setManaged(true); // Força o gerenciador de layout a considerar o espaço físico do painel no cálculo da tela

        boxForum.setVisible(false); // Oculta o painel do Fórum dos olhos do usuário
        boxForum.setManaged(false); // Retira o espaço físico do Fórum do gerenciamento de layout (colapsa o componente)

        boxTodosCompromissos.setVisible(false); // Oculta a visualização da listagem geral
        boxTodosCompromissos.setManaged(false); // Colapsa o espaço físico do componente de listagem geral

        // Altera dinamicamente o visual em CSS do botão Dashboard para denotar estado ativo (borda azul inferior com 2px de espessura)
        btnDashboard.setStyle("-fx-text-fill: #0056C6; -fx-font-weight: bold; -fx-border-color: #0056C6; -fx-border-width: 0 0 2 0; -fx-background-color: transparent;");
        btnForum.setStyle("-fx-text-fill: #718096; -fx-background-color: transparent;"); // Reseta o botão do fórum para visual inativo cinza
    }

    // Método de navegação que ativa a visibilidade da aba do Fórum e desativa as seções concorrentes
    @FXML
    void mostrarForum() {
        boxDashboard.setVisible(false); // Oculta o bloco de componentes do Dashboard
        boxDashboard.setManaged(false); // Colapsa o layout do Dashboard

        boxForum.setVisible(true); // Habilita a visualização do Fórum da comunidade
        boxForum.setManaged(true); // Ativa o gerenciamento de espaço para acomodar o Fórum

        boxTodosCompromissos.setVisible(false); // Oculta o painel de listagem completa de registros
        boxTodosCompromissos.setManaged(false); // Colapsa o layout de listagem completa de registros

        // Aplica o destaque estético de seleção ativa (borda azul sublinhada) sobre o botão do fórum
        btnForum.setStyle("-fx-text-fill: #0056C6; -fx-font-weight: bold; -fx-border-color: #0056C6; -fx-border-width: 0 0 2 0; -fx-background-color: transparent;");
        btnDashboard.setStyle("-fx-text-fill: #718096; -fx-background-color: transparent;"); // Neutraliza o botão do dashboard para cinza
    }

    // Método de navegação acionado por atalhos ou links para inspecionar a listagem unificada de tarefas
    @FXML
    void mostrarTodosCompromissos() {
        montarTodosCompromissos(); // Atualiza em tempo real a listagem limpando e redesenhando os cards de registros

        boxDashboard.setVisible(false); // Oculta e colapsa a exibição do dashboard
        boxDashboard.setManaged(false);

        boxForum.setVisible(false); // Oculta e colapsa a exibição do fórum
        boxForum.setManaged(false);

        boxTodosCompromissos.setVisible(true); // Habilita e gerencia o layout para apresentar a tela de todos os compromissos
        boxTodosCompromissos.setManaged(true);
    }

    // Aciona a abertura da janela flutuante passando um valor nulo, indicando que o intuito é criar um registro do zero
    @FXML
    void abrirModalCriarEvento() {
        abrirModalEvento(null); // Delega para a rotina comum passando 'null' como parâmetro de distinção
    }

    // Aciona a abertura da janela flutuante injetando o objeto selecionado para preencher os campos do formulário para edição
    private void abrirModalEditarEvento(EventoItem evento) {
        abrirModalEvento(evento); // Encaminha a referência do modelo existente para a rotina compartilhada
    }

    // Rotina centralizada que infla a interface de formulário de eventos, injeta dependências e controla a modalidade de exibição
    private void abrirModalEvento(EventoItem eventoEditando) {
        try {
            // Inicializa o gerenciador apontando para o arquivo de interface do formulário pop-up (criar-evento-view.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("criar-evento-view.fxml"));
            // Infla o FXML gerando uma nova cena gráfica isolada em memória
            Scene scene = new Scene(loader.load());

            // Recupera o objeto controlador instanciado do formulário secundário (CriarEventoController)
            CriarEventoController controller = loader.getController();

            // Diferenciação de fluxo: se o objeto não veio nulo, instrui o controlador secundário a popular os campos para edição
            if (eventoEditando != null) {
                controller.carregarEvento(eventoEditando);
            }

            // Define por meio de uma expressão lambda a lógica Callback (Consumer) que será executada após o botão salvar da outra tela ser acionado
            controller.setAoSalvar(eventoSalvo -> {
                // Se a operação partiu de uma criação limpa, anexa o novo objeto salvo à coleção local da lista de eventos
                if (eventoEditando == null) {
                    eventos.add(eventoSalvo);
                }

                // Força a atualização simultânea de todas as frentes de amostragem visual do sistema para refletir a alteração
                montarCalendario();
                montarProximosCompromissos();
                montarTodosCompromissos();
            });

            // Cria uma janela (Stage) isolada totalmente nova para sustentar o pop-up
            Stage stage = new Stage();
            // Aplica um operador ternário na barra de título variando o texto dinamicamente baseado na ação pretendida
            stage.setTitle(eventoEditando == null ? "Criar Compromisso" : "Editar Compromisso");
            stage.setScene(scene); // Acopla a cena do formulário na janela secundária
            // Define o tipo de modalidade APPLICATION_MODAL, travando os cliques na janela principal de trás enquanto este pop-up estiver aberto
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false); // Bloqueia o redimensionamento manual da janela pelo usuário por consistência visual
            stage.showAndWait(); // Apresenta o pop-up na tela e congela a execução do código desta linha até que o usuário feche a subjanela

        } catch (IOException e) {
            e.printStackTrace(); // Imprime o rastreio da pilha de execução em caso de pane ao ler o FXML
        }
    }

    // Realiza a chamada HTTP GET de sincronização com o servidor backend para baixar os registros do usuário logado
    private void carregarEventosDoUsuario() {
        try {
            eventos.clear(); // Limpa as referências da lista interna local para evitar acúmulo de dados duplicados

            Long idUsuario = SessaoUsuario.getIdUsuario(); // Consulta o ID numérico identificador do usuário ativo na sessão

            // Tratamento preventivo: se o ID for nulo, encerra o processamento por falta de credenciais válidas de consulta
            if (idUsuario == null) {
                System.out.println("Nenhum usuário logado.");
                return;
            }

            // Realiza uma requisição síncrona GET enviando o ID no caminho da URL e armazena a string JSON de resposta
            String resposta = ApiClient.get("/Evento/usuario/" + idUsuario);

            System.out.println("Eventos recebidos do back-end: " + resposta); // Imprime a resposta no console para auditoria

            // Instancia o núcleo da biblioteca Gson da Google para possibilitar a desserialização de cadeias de texto JSON
            com.google.gson.Gson gson = new com.google.gson.Gson();

            // Converte a string de texto JSON purificada em um vetor contendo objetos mapeados da classe de transferência EventoApiDTO
            EventoApiDTO[] eventosApi = gson.fromJson(resposta, EventoApiDTO[].class);

            // Se o servidor backend retornar uma resposta vazia ou nula, aborta a execução do laço de preenchimento
            if (eventosApi == null) {
                return;
            }

            // Percorre o vetor de DTOs convertendo e repassando os dados estruturalmente para a entidade interna de exibição EventoItem
            for (EventoApiDTO dto : eventosApi) {
                EventoItem evento = new EventoItem(); // Cria a instância limpa do modelo da lista

                // Transpõe os atributos de texto simples diretamente por meio dos métodos setters da entidade
                evento.setIdEvento(dto.getIdEvento());
                evento.setTitulo(dto.getTitulo());
                evento.setDescricao(dto.getDescricao());
                evento.setTipoEvento(dto.getTipoEvento());
                evento.setPrioridade(dto.getPrioridade());
                evento.setMateriaRelacionada(dto.getMateriaRelacionada());

                // Se houver registro de data textual no DTO, converte a cadeia de texto na classe nativa LocalDate do Java Time API
                if (dto.getDataEvento() != null) {
                    evento.setDataEvento(java.time.LocalDate.parse(dto.getDataEvento()));
                }

                // Se houver registro de horário inicial, faz a análise sintática textual convertendo em instância LocalTime
                if (dto.getHorarioInicio() != null) {
                    evento.setHorarioInicio(java.time.LocalTime.parse(dto.getHorarioInicio()));
                }

                // Se houver registro de horário de encerramento, converte textualmente para tipo manipulável LocalTime
                if (dto.getHorarioFim() != null) {
                    evento.setHorarioFim(java.time.LocalTime.parse(dto.getHorarioFim()));
                }

                eventos.add(evento); // Adiciona o compromisso devidamente convertido e tipado na coleção local do sistema
            }

        } catch (Exception e) {
            e.printStackTrace(); // Captura e imprime falhas críticas de parseamento de JSON ou erros de conexão de internet
        }
    }

    // Reseta o estado do calendário do sistema reorientando a visão imediatamente para a folha do mês e ano correntes
    @FXML
    void irParaHoje() {
        mesAtual = YearMonth.now(); // Captura novamente a estampa de tempo do mês e ano vigentes no calendário real
        montarCalendario(); // Redesenha do zero a grade visual do calendário atualizando os compromissos para a visão do mês de hoje
    }
}