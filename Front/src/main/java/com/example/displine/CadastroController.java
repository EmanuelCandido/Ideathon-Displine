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
                "Mecânica",
                "Engenharia de Software"
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

            mostrarSucesso("Cadastro realizado com sucesso!");

            abrirDashboard();

        } catch (Exception e) {
            mostrarErro(formatarErroCadastro(e.getMessage()));
            e.printStackTrace();
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
            mostrarErro("Não foi possível abrir o dashboard.");
            e.printStackTrace();
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
            mostrarErro("Não foi possível abrir a tela de login.");
            e.printStackTrace();
        }
    }

    private void mostrarSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Operação realizada com sucesso");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Verifique os campos abaixo:");

        Label label = new Label(mensagem);
        label.setWrapText(true);
        label.setMaxWidth(400);

        alert.getDialogPane().setContent(label);
        alert.getDialogPane().setPrefWidth(480);

        alert.showAndWait();
    }

    private String formatarErroCadastro(String erroBruto) {
        if (erroBruto == null || erroBruto.isBlank()) {
            return "Ocorreu um erro inesperado.";
        }

        StringBuilder sb = new StringBuilder();

        if (erroBruto.contains("nome")) {
            sb.append("• O nome é obrigatório.\n");
        }

        if (erroBruto.contains("email")) {
            sb.append("• Informe um e-mail válido.\n");
        }

        if (erroBruto.contains("senha")) {
            sb.append("• A senha é obrigatória e precisa ter no mínimo 4 caracteres.\n");
        }

        if (erroBruto.contains("matricula")) {
            sb.append("• A matrícula é obrigatória.\n");
        }

        if (erroBruto.contains("turma")) {
            sb.append("• A turma é obrigatória.\n");
        }

        if (erroBruto.contains("curso")) {
            sb.append("• Selecione um curso.\n");
        }

        if (erroBruto.contains("duplicate")
                || erroBruto.contains("duplicar")
                || erroBruto.contains("unique")
                || erroBruto.contains("constraint")) {
            sb.append("• Já existe um cadastro com esse e-mail ou matrícula.\n");
        }

        if (sb.isEmpty()) {
            return "Não foi possível realizar o cadastro.\nVerifique os dados informados e tente novamente.";
        }

        return sb.toString();
    }
}