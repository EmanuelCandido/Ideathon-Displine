package com.example.displine; // Declaração do pacote (diretório) onde a classe inicial do sistema está localizada

// Importações necessárias da biblioteca do JavaFX para inicialização da aplicação e carregamento de janelas
import javafx.application.Application; // Classe base do JavaFX da qual qualquer aplicativo gráfico deve herdar
import javafx.fxml.FXMLLoader; // Classe usada para ler os arquivos de interface estruturados em XML (.fxml) e convertê-los em nós visuais
import javafx.scene.Scene; // Container de conteúdo interno (a cena) onde os componentes visuais são renderizados dentro de uma janela
import javafx.stage.Stage; // A janela física primordial (palco) disponibilizada pelo sistema operacional

import java.io.IOException; // Classe de exceção nativa para tratar possíveis falhas na leitura física do arquivo .fxml

// Definição da classe HelloApplication que estende (herda de) Application, tornando-se o ponto de partida do ciclo de vida JavaFX
public class HelloApplication extends Application {

    // Sobrescrita do método abstrato start da classe Application
    // Este é o método principal de entrada da interface gráfica, onde a janela primária (Stage) é fornecida automaticamente pelo JavaFX
    @Override
    public void start(Stage stage) throws IOException {

        // Agora carrega a tela de login como a primeira do sistema
        // Instancia o carregador de FXML e aponta o caminho relativo para localizar o arquivo do layout de login (login-view.fxml)
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));

        // Cria uma nova cena gráfica na memória carregando (inflando) a estrutura de nós do FXML
        // e definindo o tamanho inicial preferencial da janela como 1000 pixels de largura por 700 pixels de altura
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);

        // Define a String de texto que aparecerá centralizada na barra de título superior da janela do sistema operacional
        stage.setTitle("Displine - Login");

        // Acopla a cena de login (com todo o seu design e elementos visuais) dentro da janela física (Stage)
        stage.setScene(scene);

        // Mantém o tamanho fixo para o card de login ficar centralizado
        // Desabilita a capacidade do usuário de arrastar as bordas da tela ou clicar no botão de maximizar para redimensionar a janela
        stage.setResizable(false);

        // Torna a janela visível na tela do computador do usuário e dá o foco de interações do sistema para ela
        stage.show();
    }

    // Método main tradicional do Java que serve como o ponto de entrada primário quando o programa é executado no terminal ou IDE
    public static void main(String[] args) {
        // Método estático Herdado da classe Application que dispara internamente toda a infraestrutura e rotinas de ciclo de vida do JavaFX,
        // chamando em seguida de forma oculta o método start() configurado logo acima
        launch();
    }
}