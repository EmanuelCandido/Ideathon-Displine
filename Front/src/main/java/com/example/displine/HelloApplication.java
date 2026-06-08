package com.example.displine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Agora carrega a tela de login como a primeira do sistema
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);

        stage.setTitle("Displine - Login");
        stage.setScene(scene);
        stage.setResizable(false); // Mantém o tamanho fixo para o card de login ficar centralizado
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}