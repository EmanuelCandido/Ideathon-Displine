package com.example.displine; // Declaração do pacote (diretório) onde a classe controladora do login está localizada

// Importações do JavaFX para tratamento de eventos, injeção de componentes de interface gráfica e controle de janelas
import javafx.event.ActionEvent; // Classe usada para capturar e gerenciar os eventos de clique originados nos botões
import javafx.fxml.FXML; // Anotação obrigatória para expor atributos e métodos privados ao arquivo de interface (.fxml)
import javafx.fxml.FXMLLoader; // Carregador encarregado de inflar e converter o arquivo XML de visualização em objetos Java
import javafx.scene.Scene; // Classe que representa a árvore de conteúdo visual exibida dentro de uma janela (palco)
import javafx.scene.control.Button; // Controle de botão interativo padrão para cliques
import javafx.scene.control.PasswordField; // Campo de texto especializado que oculta os caracteres digitados (máscara de senha)
import javafx.scene.control.TextField; // Campo de texto em linha única para digitação de dados comuns (como e-mail)
import javafx.stage.Stage; // Classe que gerencia a janela física primordial disponibilizada pelo sistema operacional
import java.io.IOException; // Classe de exceção nativa para monitoramento de erros de entrada/saída na leitura dos arquivos FXML

// Classe de controle responsável por gerenciar a tela de autenticação dos usuários do sistema
public class LoginController {

    // Componentes visuais injetados do arquivo FXML por meio dos seus respectivos identificadores fx:id
    @FXML private TextField txtEmail; // Caixa de digitação destinada a coletar o endereço de e-mail do usuário
    @FXML private PasswordField txtSenha; // Caixa de digitação destinada a receber a senha mascarada do usuário
    @FXML private Button btnEntrar; // Botão de disparo para validar as credenciais e efetuar a autenticação

    // Método disparado quando o usuário aciona o botão "Entrar" (onAction)
    @FXML
    void realizarLogin(ActionEvent event) {
        try {
            // Recupera o texto literal contido no interior de cada caixa de preenchimento
            String email = txtEmail.getText();
            String senha = txtSenha.getText();

            // Monta em tempo de execução o corpo da requisição (payload) estruturado como um bloco textual JSON
            String json = """
                {
                    "email": "%s",
                    "senha": "%s"
                }
                """.formatted(
                    // Higieniza as cadeias de caracteres através do método estático limpar() da classe ApiClient
                    ApiClient.limpar(email),
                    ApiClient.limpar(senha)
            );

            // Realiza uma chamada síncrona HTTP POST para o endpoint de autenticação e armazena a resposta em texto da API
            String resposta = ApiClient.post("/Usuario/login", json);

            // Analisa a String JSON retornada para isolar e extrair os dados individuais do usuário autenticado
            Long idUsuario = extrairLong(resposta, "id"); // Extrai o ID único em formato numérico longo
            String nome = extrairTexto(resposta, "nome"); // Extrai o nome de exibição do usuário
            String emailUsuario = extrairTexto(resposta, "email"); // Extrai o e-mail confirmado pela API

            // Salva as informações extraídas nos atributos estáticos da classe de controle de SessaoUsuario
            SessaoUsuario.setIdUsuario(idUsuario);
            SessaoUsuario.setNome(nome);
            SessaoUsuario.setEmail(emailUsuario);



            // Avança para a próxima etapa do fluxo da aplicação abrindo a tela principal do sistema
            abrirDashboard();

        } catch (Exception e) {
            // Em caso de credenciais recusadas (erro HTTP 401) ou indisponibilidade do backend, emite um alerta de erro
            mostrarAlerta("Erro no login", "E-mail ou senha inválidos.");
            e.printStackTrace(); // Registra no console do desenvolvedor a pilha de rastreamento do erro ocorrido
        }
    }

    // Método acionado pelo botão ou link que direciona o usuário para o formulário de novos cadastros
    @FXML
    void irParaCadastro(ActionEvent event) {
        // Redireciona o conteúdo da janela atual trocando para a visão de criação de novas contas
        trocarTela("cadastro-view.fxml", "Displine - Criar Conta");
    }

    // Rotina privada focada em inicializar a janela principal do sistema em modo maximizado e fechar a tela de login anterior
    private void abrirDashboard() {
        try {
            // Prepara a leitura do arquivo FXML que contém a interface do painel principal (hello-view.fxml)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            // Carrega e monta o grafo de nós visuais gerando uma nova cena gráfica independente
            Scene scene = new Scene(fxmlLoader.load());

            // Localiza e recupera a referência da janela atual de login subindo a árvore gráfica a partir do botão entrar
            Stage stageAtual = (Stage) btnEntrar.getScene().getWindow();

            // Instancia uma janela (Stage) física completamente nova para ancorar o painel
            Stage novoStage = new Stage();
            novoStage.setTitle("Displine - Dashboard"); // Define o título na barra superior do sistema operacional
            novoStage.setScene(scene); // Acopla a cena do painel na nova janela configurada
            novoStage.setMaximized(true); // Força a nova janela a abrir em modo de tela cheia (maximizado)
            novoStage.show(); // Exibe a nova janela física do painel na tela do monitor

            stageAtual.close(); // Executa o encerramento e descarte definitivo da janela de login anterior
        } catch (IOException e) {
            e.printStackTrace(); // Captura falhas de carregamento ou formatação no arquivo de visualização XML do painel
        }
    }

    // Rotina genérica usada para reaproveitar a mesma janela física atual substituindo apenas a cena interna exposta
    private void trocarTela(String fxml, String titulo) {
        try {
            // Imprime no console a URL de resolução do recurso para fins de monitoramento e auditoria em desenvolvimento
            System.out.println("Abrindo FXML: " + getClass().getResource(fxml));

            // Instancia o carregador dinâmico apontando para o arquivo XML recebido por parâmetro
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            // Infla os elementos do arquivo construindo um novo container de cena
            Scene scene = new Scene(fxmlLoader.load());

            // Captura o Stage atual que abriga as interações através do contexto gráfico do botão de entrada
            Stage stage = (Stage) btnEntrar.getScene().getWindow();
            stage.setTitle(titulo); // Redefine o título textual da barra superior da janela
            stage.setScene(scene); // Substitui a cena de login antiga pela nova cena carregada (ex: tela de cadastro)
        } catch (IOException e) {
            e.printStackTrace(); // Trata falhas físicas de leitura associadas à localização do arquivo FXML
        }
    }

    // Constrói e apresenta uma caixa modal gráfica nativa padrão para relatar falhas e avisos de erro na interface
    private void mostrarAlerta(String titulo, String mensagem) {
        // Instancia o componente nativo de diálogo configurando o ícone de aviso como ERROR (ícone vermelho de interrupção)
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(titulo); // Configura o cabeçalho textual da janela de aviso
        alert.setHeaderText(null); // Desativa o subcabeçalho padrão cinza para deixar o layout mais limpo e direto
        alert.setContentText(mensagem); // Define o texto descritivo centralizado com o erro sofrido pelo usuário
        alert.showAndWait(); // Apresenta a janela modal na tela travando o fluxo até que o botão "OK" de dispensa seja acionado
    }

    // Mecanismo de varredura de texto (Parser) focado em extrair campos numéricos de tipo Long de dentro do JSON bruto da API
    private Long extrairLong(String json, String campo) {
        try {
            // Constrói a chave padrão de localização baseada na estrutura do JSON (ex: "id":)
            String busca = "\"" + campo + "\":";
            int inicio = json.indexOf(busca); // Busca pelo índice numérico inicial onde a chave é encontrada no texto

            if (inicio == -1) { // Se retornar o índice -1, significa que o atributo pesquisado não existe na resposta do servidor
                return null;
            }

            inicio += busca.length(); // Move o ponteiro para pular os caracteres da chave e focar no início do valor numérico

            int fim = json.indexOf(",", inicio); // Busca pela primeira vírgula após o valor para determinar o fim do campo

            if (fim == -1) { // Caso o campo seja o último do bloco JSON, determina o término localizando o caractere de chaves
                fim = json.indexOf("}", inicio);
            }

            // Realiza o recorte textual da substring correspondente ao número, eliminando espaços vazios residuais com trim()
            String valor = json.substring(inicio, fim).trim();

            return Long.parseLong(valor); // Converte a String de texto isolada em uma instância numérica válida de tipo Long

        } catch (Exception e) {
            return null; // Retorna nulo defensivamente caso ocorra qualquer erro de segmentação na cadeia de caracteres
        }
    }

    // Mecanismo de varredura de texto (Parser) configurado para capturar e isolar valores de atributos do tipo String no JSON
    private String extrairTexto(String json, String campo) {
        try {
            // Monta o padrão de busca considerando as aspas que abrem o valor textual do JSON (ex: "nome":")
            String busca = "\"" + campo + "\":\"";
            int inicio = json.indexOf(busca); // Localiza a posição de partida do padrão textual na mensagem

            if (inicio == -1) { // Se não localizar a correspondência exata, retorna uma String limpa vazia por segurança
                return "";
            }

            inicio += busca.length(); // Desloca o ponteiro para pular a marcação inicial posicionando-o no primeiro caractere do conteúdo

            int fim = json.indexOf("\"", inicio); // Localiza a aspa de fechamento que delimita o término da string do valor daquele atributo

            return json.substring(inicio, fim); // Recorta e retorna precisamente o intervalo textual contido entre as aspas do JSON

        } catch (Exception e) {
            return ""; // Retorna string vazia padrão em caso de anomalias no parseamento ou fatiamento do texto do JSON
        }
    }
}