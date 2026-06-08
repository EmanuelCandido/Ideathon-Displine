package com.example.displine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroController {

    @FXML private TextField txtNome;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtSenha;
    @FXML private TextField txtMatricula;
    @FXML private ComboBox<String> comboCurso;
    @FXML private TextField txtTurma;
    @FXML private Button btnCadastrar;

    @FXML
    public void initialize() {
        comboCurso.getItems().addAll(
                "Informática",
                "Redes de Computadores",
                "Eletrotécnica",
                "Mecânica"
        );
    }

    @FXML
    void realizarCadastro(ActionEvent event) {
        try {
            String nome = txtNome.getText();
            String email = txtEmail.getText();
            String senha = txtSenha.getText();
            String matricula = txtMatricula.getText();
            String curso = comboCurso.getValue();
            String turma = txtTurma.getText();

            String json = """
                    {
                        "nome": "%s",
                        "email": "%s",
                        "senha": "%s",
                        "matricula": "%s",
                        "turma": "%s",
                        "curso": "%s"
                    }
                    """.formatted(
                    ApiClient.limpar(nome),
                    ApiClient.limpar(email),
                    ApiClient.limpar(senha),
                    ApiClient.limpar(matricula),
                    ApiClient.limpar(turma),
                    ApiClient.limpar(curso)
            );

            ApiClient.post("/Aluno", json);

            mostrarAlerta("Sucesso", "Cadastro realizado com sucesso!");
            abrirDashboard();

        } catch (Exception e) {
            mostrarErroFormatado("Erro ao cadastrar", e.getMessage());
        }
    }

    private void abrirDashboard() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stageAtual = (Stage) btnCadastrar.getScene().getWindow();

            Stage novoStage = new Stage();
            novoStage.setTitle("Displine - Dashboard");
            novoStage.setScene(scene);
            novoStage.setMaximized(true);
            novoStage.show();

            stageAtual.close();

        } catch (IOException e) {
            mostrarAlerta("Erro", "Não foi possível abrir o dashboard.");
        }
    }

    @FXML
    void irParaLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnCadastrar.getScene().getWindow();
            stage.setTitle("Displine - Login");
            stage.setScene(scene);

        } catch (IOException e) {
            mostrarAlerta("Erro", "Não foi possível abrir a tela de login.");
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText("Verifique os campos abaixo:");

        Label label = new Label(mensagem);
        label.setWrapText(true);
        label.setMaxWidth(350);

        alert.getDialogPane().setContent(label);
        alert.getDialogPane().setPrefWidth(420);

        alert.showAndWait();
    }

    private void mostrarErroFormatado(String titulo, String erroBruto) {
        String mensagemFormatada = formatarMensagemErro(erroBruto);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText("Não foi possível concluir o cadastro");

        Label label = new Label(mensagemFormatada);
        label.setWrapText(true);
        label.setMaxWidth(380);

        alert.getDialogPane().setContent(label);
        alert.getDialogPane().setPrefWidth(450);

        alert.showAndWait();
    }

    private String formatarMensagemErro(String erroBruto) {
        if (erroBruto == null || erroBruto.isBlank()) {
            return "Ocorreu um erro inesperado.";
        }

        StringBuilder sb = new StringBuilder();

        if (erroBruto.contains("nome")) {
            sb.append("• O nome é obrigatório\n");
        }
        if (erroBruto.contains("email")) {
            sb.append("• O e-mail é obrigatório\n");
        }
        if (erroBruto.contains("matricula")) {
            sb.append("• A matrícula é obrigatória\n");
        }
        if (erroBruto.contains("turma")) {
            sb.append("• A turma é obrigatória\n");
        }
        if (erroBruto.contains("curso")) {
            sb.append("• O curso é obrigatório\n");
        }
        if (erroBruto.contains("senha")) {
            sb.append("• A senha deve ter no mínimo 4 caracteres\n");
        }

        if (sb.isEmpty()) {
            return erroBruto;
        }

        return sb.toString();
    }
}