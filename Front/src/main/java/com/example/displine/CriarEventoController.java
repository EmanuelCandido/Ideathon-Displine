package com.example.displine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.function.Consumer;

public class CriarEventoController {

    @FXML private TextField txtTitulo;
    @FXML private ComboBox<String> comboTipo;
    @FXML private TextField txtMateria;
    @FXML private DatePicker datePicker;
    @FXML private TextField txtInicio;
    @FXML private TextField txtFim;
    @FXML private TextArea txtDescricao;

    @FXML private Button btnFacil;
    @FXML private Button btnMedio;
    @FXML private Button btnAlto;
    @FXML private Button btnUrgente;
    @FXML private Button btnCancelar;

    private String prioridadeSelecionada = "";
    private EventoItem eventoEditando;
    private Consumer<EventoItem> aoSalvar;

    @FXML
    public void initialize() {
        comboTipo.getItems().addAll("Aula", "Prova", "Trabalho", "Estudo", "Outro");
    }

    public void setAoSalvar(Consumer<EventoItem> aoSalvar) {
        this.aoSalvar = aoSalvar;
    }

    public void carregarEvento(EventoItem evento) {
        this.eventoEditando = evento;

        txtTitulo.setText(evento.getTitulo());
        comboTipo.setValue(evento.getTipoEvento());
        txtMateria.setText(evento.getMateriaRelacionada());
        datePicker.setValue(evento.getDataEvento());
        txtInicio.setText(String.valueOf(evento.getHorarioInicio()));
        txtFim.setText(String.valueOf(evento.getHorarioFim()));
        txtDescricao.setText(evento.getDescricao());

        prioridadeSelecionada = evento.getPrioridade();
        marcarPrioridade(prioridadeSelecionada);
    }

    @FXML
    void selecionarPrioridade(ActionEvent event) {
        Button btnClicado = (Button) event.getSource();

        prioridadeSelecionada = btnClicado.getText();
        marcarPrioridade(prioridadeSelecionada);
    }

    private void marcarPrioridade(String prioridade) {
        resetarBotaoPrioridade(btnFacil);
        resetarBotaoPrioridade(btnMedio);
        resetarBotaoPrioridade(btnAlto);
        resetarBotaoPrioridade(btnUrgente);

        if ("Fácil".equals(prioridade)) {
            destacar(btnFacil, "#EBF8FF", "#2B6CB0", "#BEE3F8");
        } else if ("Médio".equals(prioridade)) {
            destacar(btnMedio, "#FEFCBF", "#975A16", "#FAF089");
        } else if ("Alto".equals(prioridade)) {
            destacar(btnAlto, "#FEEBC8", "#DD6B20", "#FBD38D");
        } else if ("Urgente".equals(prioridade)) {
            destacar(btnUrgente, "#FED7D7", "#C53030", "#FEB2B2");
        }
    }

    private void destacar(Button btn, String fundo, String texto, String borda) {
        btn.setStyle("-fx-background-color: " + fundo + "; -fx-text-fill: " + texto + "; -fx-border-color: " + borda + "; -fx-border-radius: 6px; -fx-background-radius: 6px; -fx-font-weight: bold;");
    }

    private void resetarBotaoPrioridade(Button btn) {
        btn.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #4A5568; -fx-border-color: #CBD5E1; -fx-border-radius: 6px; -fx-background-radius: 6px;");
    }

    @FXML
    void salvarEvento(ActionEvent event) {
        try {
            if (txtTitulo.getText().isBlank() || datePicker.getValue() == null || txtInicio.getText().isBlank()) {
                mostrarAlerta("Erro", "Preencha título, data e horário de início.");
                return;
            }

            EventoItem evento;

            if (eventoEditando == null) {
                evento = new EventoItem();
            } else {
                evento = eventoEditando;
            }

            evento.setTitulo(txtTitulo.getText());
            evento.setTipoEvento(comboTipo.getValue());
            evento.setMateriaRelacionada(txtMateria.getText());
            evento.setDataEvento(datePicker.getValue());
            evento.setHorarioInicio(LocalTime.parse(txtInicio.getText()));
            evento.setHorarioFim(txtFim.getText().isBlank() ? LocalTime.parse(txtInicio.getText()) : LocalTime.parse(txtFim.getText()));
            evento.setDescricao(txtDescricao.getText());
            evento.setPrioridade(prioridadeSelecionada.isBlank() ? "Fácil" : prioridadeSelecionada);

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
                    SessaoUsuario.getIdUsuario(),
                    evento.getDataEvento(),
                    ApiClient.limpar(evento.getTitulo()),
                    ApiClient.limpar(evento.getDescricao()),
                    evento.getHorarioInicio(),
                    evento.getHorarioFim(),
                    ApiClient.limpar(evento.getTipoEvento()),
                    ApiClient.limpar(evento.getPrioridade()),
                    ApiClient.limpar(evento.getMateriaRelacionada())
            );

            String resposta;

            if (evento.getIdEvento() == null) {
                resposta = ApiClient.post("/Evento", json);

                Long idGerado = extrairIdEvento(resposta);
                evento.setIdEvento(idGerado);
            } else {
                resposta = ApiClient.put("/Evento/" + evento.getIdEvento(), json);
            }

            if (aoSalvar != null) {
                aoSalvar.accept(evento);
            }

            mostrarAlerta("Sucesso", "Compromisso salvo com sucesso!");
            fecharJanela();

        } catch (Exception e) {
            mostrarErroFormatado("Erro ao salvar", e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void fecharJanela() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private Long extrairIdEvento(String json) {
        try {
            String campo = "\"idEvento\":";
            int inicio = json.indexOf(campo);

            if (inicio == -1) {
                return null;
            }

            inicio += campo.length();

            int fim = json.indexOf(",", inicio);

            if (fim == -1) {
                fim = json.indexOf("}", inicio);
            }

            String valor = json.substring(inicio, fim).trim();

            return Long.parseLong(valor);

        } catch (Exception e) {
            return null;
        }
    }

    private void mostrarErroFormatado(String titulo, String erroBruto) {
        String mensagemFormatada = formatarMensagemErro(erroBruto);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText("Não foi possível salvar o compromisso");

        Label label = new Label(mensagemFormatada);
        label.setWrapText(true);
        label.setMaxWidth(400);

        alert.getDialogPane().setContent(label);
        alert.getDialogPane().setPrefWidth(470);

        alert.showAndWait();
    }

    private String formatarMensagemErro(String erroBruto) {
        if (erroBruto == null || erroBruto.isBlank()) {
            return "Ocorreu um erro inesperado.";
        }

        StringBuilder sb = new StringBuilder();

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

        if (sb.isEmpty()) {
            return "Verifique os dados preenchidos e tente novamente.";
        }

        return sb.toString();
    }
}