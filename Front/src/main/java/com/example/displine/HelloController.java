package com.example.displine;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class HelloController {

    @FXML private GridPane gridCalendario;
    @FXML private Label lblMesAno;

    @FXML private Label lblSaudacao;
    @FXML private VBox boxDashboard;
    @FXML private VBox boxForum;
    @FXML private VBox boxProximos;
    @FXML private VBox boxTodosCompromissos;
    @FXML private VBox boxListaTodosCompromissos;

    @FXML private Button btnDashboard;
    @FXML private Button btnForum;

    private YearMonth mesAtual;
    private final List<EventoItem> eventos = new ArrayList<>();

    @FXML
    public void initialize() {
        mesAtual = YearMonth.now();

        if (SessaoUsuario.getNome() != null && !SessaoUsuario.getNome().isBlank()) {
            lblSaudacao.setText("Olá, " + SessaoUsuario.getNome() + "!");
        } else {
            lblSaudacao.setText("Olá!");
        }

        carregarEventosDoUsuario();

        mostrarDashboard();
        montarCalendario();
        montarProximosCompromissos();
        montarTodosCompromissos();
        montarForum();
    }

    private void montarCalendario() {
        gridCalendario.getChildren().clear();
        atualizarTituloMesAno();

        String[] diasDaSemana = {"DOM", "SEG", "TER", "QUA", "QUI", "SEX", "SÁB"};

        for (int i = 0; i < diasDaSemana.length; i++) {
            Label labelDia = new Label(diasDaSemana[i]);
            labelDia.setStyle("-fx-font-weight: bold; -fx-text-fill: #718096; -fx-font-size: 11px;");

            VBox header = new VBox(labelDia);
            header.setAlignment(Pos.CENTER);
            header.setStyle("-fx-padding: 10; -fx-border-color: #EDF2F7; -fx-border-width: 0.5;");
            gridCalendario.add(header, i, 0);
        }

        LocalDate primeiroDia = mesAtual.atDay(1);
        int coluna = primeiroDia.getDayOfWeek().getValue() % 7;
        int linha = 1;

        for (int dia = 1; dia <= mesAtual.lengthOfMonth(); dia++) {
            LocalDate data = mesAtual.atDay(dia);

            Label lblNumero = new Label(String.valueOf(dia));
            lblNumero.setStyle("-fx-font-size: 12px; -fx-text-fill: #2D3748;");

            VBox celula = new VBox(5);
            celula.getChildren().add(lblNumero);

            if (data.equals(LocalDate.now())) {
                celula.setStyle(
                        "-fx-border-color: #0056C6;" +
                                "-fx-border-width: 2;" +
                                "-fx-padding: 8;" +
                                "-fx-min-width: 95;" +
                                "-fx-min-height: 72;" +
                                "-fx-background-color: #EBF8FF;"
                );

                lblNumero.setStyle(
                        "-fx-font-size: 12px;" +
                                "-fx-text-fill: #0056C6;" +
                                "-fx-font-weight: bold;"
                );
            } else {
                celula.setStyle(
                        "-fx-border-color: #EDF2F7;" +
                                "-fx-border-width: 0.5;" +
                                "-fx-padding: 8;" +
                                "-fx-min-width: 95;" +
                                "-fx-min-height: 72;"
                );
            }
            for (EventoItem evento : eventos) {
                if (evento.getDataEvento().equals(data)) {
                    Label lblEvento = new Label(evento.getHorarioInicio() + " " + evento.getTitulo());
                    lblEvento.setStyle("-fx-background-color: #EBF8FF; -fx-text-fill: #2B6CB0; -fx-font-size: 9px; -fx-padding: 3; -fx-background-radius: 3; -fx-cursor: hand;");
                    lblEvento.setOnMouseClicked(e -> abrirModalEditarEvento(evento));
                    celula.getChildren().add(lblEvento);
                }
            }

            gridCalendario.add(celula, coluna, linha);

            coluna++;
            if (coluna > 6) {
                coluna = 0;
                linha++;
            }
        }
    }

    private void montarProximosCompromissos() {
        boxProximos.getChildren().clear();

        eventos.stream()
                .filter(e -> !e.getDataEvento().isBefore(LocalDate.now()))
                .sorted(Comparator.comparing(EventoItem::getDataEvento).thenComparing(EventoItem::getHorarioInicio))
                .limit(5)
                .forEach(evento -> boxProximos.getChildren().add(criarCardCompromisso(evento)));

        if (boxProximos.getChildren().isEmpty()) {
            Label vazio = new Label("Nenhum compromisso cadastrado.");
            vazio.setStyle("-fx-text-fill: #718096; -fx-font-size: 13px;");
            boxProximos.getChildren().add(vazio);
        }
    }

    private void montarTodosCompromissos() {
        boxListaTodosCompromissos.getChildren().clear();

        eventos.stream()
                .sorted(Comparator.comparing(EventoItem::getDataEvento).thenComparing(EventoItem::getHorarioInicio))
                .forEach(evento -> boxListaTodosCompromissos.getChildren().add(criarCardCompromisso(evento)));

        if (boxListaTodosCompromissos.getChildren().isEmpty()) {
            Label vazio = new Label("Nenhum compromisso cadastrado.");
            vazio.setStyle("-fx-text-fill: #718096; -fx-font-size: 13px;");
            boxListaTodosCompromissos.getChildren().add(vazio);
        }
    }

    private VBox criarCardCompromisso(EventoItem evento) {
        VBox card = new VBox(4);

        String prioridade = evento.getPrioridade() == null ? "" : evento.getPrioridade();

        String corBorda = switch (prioridade) {
            case "Urgente" -> "#E53E3E";
            case "Alto" -> "#DD6B20";
            case "Médio" -> "#D69E2E";
            default -> "#38A169";
        };

        card.setStyle(
                "-fx-background-color: #F7FAFC;" +
                        "-fx-padding: 12;" +
                        "-fx-background-radius: 6;" +
                        "-fx-border-color: " + corBorda + ";" +
                        "-fx-border-width: 0 0 0 4;" +
                        "-fx-cursor: hand;"
        );

        Label titulo = new Label(evento.getTitulo());
        titulo.setStyle("-fx-font-weight: bold; -fx-text-fill: #1A202C; -fx-font-size: 14px;");

        Label info = new Label("📅 " + evento.getDataEvento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                "  •  ⏱ " + evento.getHorarioInicio());
        info.setStyle("-fx-text-fill: #718096; -fx-font-size: 12px;");

        Label descricao = new Label();

        if (evento.getDescricao() != null && !evento.getDescricao().isBlank()) {
            descricao.setText(evento.getDescricao());
        } else {
            descricao.setText("Sem descrição.");
        }

        descricao.setWrapText(true);
        descricao.setMaxWidth(600);
        descricao.setStyle("-fx-text-fill: #4A5568; -fx-font-size: 12px;");

        card.getChildren().addAll(titulo, info, descricao);
        card.setOnMouseClicked(e -> abrirModalEditarEvento(evento));

        return card;
    }

    private void montarForum() {
        boxForum.getChildren().clear();

        Label titulo = new Label("Fórum da Comunidade");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #1A202C;");

        VBox card = new VBox(8);
        card.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 18; -fx-background-radius: 8; -fx-border-color: #E2E8F0; -fx-border-radius: 8;");

        Label topico = new Label("Nenhuma discussão cadastrada.");
        topico.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #1A202C;");

        Label texto = new Label("As dúvidas e discussões da comunidade aparecerão aqui.");
        texto.setStyle("-fx-font-size: 13px; -fx-text-fill: #718096;");

        card.getChildren().addAll(topico, texto);
        boxForum.getChildren().addAll(titulo, card);
    }

    private void atualizarTituloMesAno() {
        String nomeMes = mesAtual.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
        nomeMes = nomeMes.substring(0, 1).toUpperCase() + nomeMes.substring(1);
        lblMesAno.setText(nomeMes + " " + mesAtual.getYear());
    }

    @FXML
    void mesAnterior() {
        mesAtual = mesAtual.minusMonths(1);
        montarCalendario();
    }

    @FXML
    void proximoMes() {
        mesAtual = mesAtual.plusMonths(1);
        montarCalendario();
    }

    @FXML
    void mostrarDashboard() {
        boxDashboard.setVisible(true);
        boxDashboard.setManaged(true);

        boxForum.setVisible(false);
        boxForum.setManaged(false);

        boxTodosCompromissos.setVisible(false);
        boxTodosCompromissos.setManaged(false);

        btnDashboard.setStyle("-fx-text-fill: #0056C6; -fx-font-weight: bold; -fx-border-color: #0056C6; -fx-border-width: 0 0 2 0; -fx-background-color: transparent;");
        btnForum.setStyle("-fx-text-fill: #718096; -fx-background-color: transparent;");
    }

    @FXML
    void mostrarForum() {
        boxDashboard.setVisible(false);
        boxDashboard.setManaged(false);

        boxForum.setVisible(true);
        boxForum.setManaged(true);

        boxTodosCompromissos.setVisible(false);
        boxTodosCompromissos.setManaged(false);

        btnForum.setStyle("-fx-text-fill: #0056C6; -fx-font-weight: bold; -fx-border-color: #0056C6; -fx-border-width: 0 0 2 0; -fx-background-color: transparent;");
        btnDashboard.setStyle("-fx-text-fill: #718096; -fx-background-color: transparent;");
    }

    @FXML
    void mostrarTodosCompromissos() {
        montarTodosCompromissos();

        boxDashboard.setVisible(false);
        boxDashboard.setManaged(false);

        boxForum.setVisible(false);
        boxForum.setManaged(false);

        boxTodosCompromissos.setVisible(true);
        boxTodosCompromissos.setManaged(true);
    }

    @FXML
    void abrirModalCriarEvento() {
        abrirModalEvento(null);
    }

    private void abrirModalEditarEvento(EventoItem evento) {
        abrirModalEvento(evento);
    }

    private void abrirModalEvento(EventoItem eventoEditando) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("criar-evento-view.fxml"));
            Scene scene = new Scene(loader.load());

            CriarEventoController controller = loader.getController();

            if (eventoEditando != null) {
                controller.carregarEvento(eventoEditando);
            }

            controller.setAoSalvar(eventoSalvo -> {
                if (eventoEditando == null) {
                    eventos.add(eventoSalvo);
                }

                montarCalendario();
                montarProximosCompromissos();
                montarTodosCompromissos();
            });

            Stage stage = new Stage();
            stage.setTitle(eventoEditando == null ? "Criar Compromisso" : "Editar Compromisso");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarEventosDoUsuario() {
        try {
            eventos.clear();

            Long idUsuario = SessaoUsuario.getIdUsuario();

            if (idUsuario == null) {
                System.out.println("Nenhum usuário logado.");
                return;
            }

            String resposta = ApiClient.get("/Evento/usuario/" + idUsuario);

            System.out.println("Eventos recebidos do back-end: " + resposta);

            com.google.gson.Gson gson = new com.google.gson.Gson();

            EventoApiDTO[] eventosApi = gson.fromJson(resposta, EventoApiDTO[].class);

            if (eventosApi == null) {
                return;
            }

            for (EventoApiDTO dto : eventosApi) {
                EventoItem evento = new EventoItem();

                evento.setIdEvento(dto.getIdEvento());
                evento.setTitulo(dto.getTitulo());
                evento.setDescricao(dto.getDescricao());
                evento.setTipoEvento(dto.getTipoEvento());
                evento.setPrioridade(dto.getPrioridade());
                evento.setMateriaRelacionada(dto.getMateriaRelacionada());

                if (dto.getDataEvento() != null) {
                    evento.setDataEvento(java.time.LocalDate.parse(dto.getDataEvento()));
                }

                if (dto.getHorarioInicio() != null) {
                    evento.setHorarioInicio(java.time.LocalTime.parse(dto.getHorarioInicio()));
                }

                if (dto.getHorarioFim() != null) {
                    evento.setHorarioFim(java.time.LocalTime.parse(dto.getHorarioFim()));
                }

                eventos.add(evento);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaHoje() {
        mesAtual = YearMonth.now();
        montarCalendario();
    }
}