package com.example.displine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML private TextField txtEmail;
    @FXML private PasswordField txtSenha;
    @FXML private Button btnEntrar;

    @FXML
    void realizarLogin(ActionEvent event) {
        try {
            String email = txtEmail.getText();
            String senha = txtSenha.getText();

            String json = """
                {
                    "email": "%s",
                    "senha": "%s"
                }
                """.formatted(
                    ApiClient.limpar(email),
                    ApiClient.limpar(senha)
            );

            String resposta = ApiClient.post("/Usuario/login", json);

            Long idUsuario = extrairLong(resposta, "id");
            String nome = extrairTexto(resposta, "nome");
            String emailUsuario = extrairTexto(resposta, "email");

            SessaoUsuario.setIdUsuario(idUsuario);
            SessaoUsuario.setNome(nome);
            SessaoUsuario.setEmail(emailUsuario);

           

            abrirDashboard();

        } catch (Exception e) {
            mostrarAlerta("Erro no login", "E-mail ou senha inválidos.");
            e.printStackTrace();
        }
    }

    @FXML
    void irParaCadastro(ActionEvent event) {
        trocarTela("cadastro-view.fxml", "Displine - Criar Conta");
    }

    private void abrirDashboard() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stageAtual = (Stage) btnEntrar.getScene().getWindow();

            Stage novoStage = new Stage();
            novoStage.setTitle("Displine - Dashboard");
            novoStage.setScene(scene);
            novoStage.setMaximized(true);
            novoStage.show();

            stageAtual.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void trocarTela(String fxml, String titulo) {
        try {
            System.out.println("Abrindo FXML: " + getClass().getResource(fxml));

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnEntrar.getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private Long extrairLong(String json, String campo) {
        try {
            String busca = "\"" + campo + "\":";
            int inicio = json.indexOf(busca);

            if (inicio == -1) {
                return null;
            }

            inicio += busca.length();

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

    private String extrairTexto(String json, String campo) {
        try {
            String busca = "\"" + campo + "\":\"";
            int inicio = json.indexOf(busca);

            if (inicio == -1) {
                return "";
            }

            inicio += busca.length();

            int fim = json.indexOf("\"", inicio);

            return json.substring(inicio, fim);

        } catch (Exception e) {
            return "";
        }
    }
}