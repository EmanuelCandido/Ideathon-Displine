package com.example.displine; // Declaração do pacote (diretório) onde a classe do controlador está localizada

// Importações do JavaFX para tratamento de eventos, injeção de componentes e gerenciamento de telas
import javafx.event.ActionEvent; // Classe que representa um evento de ação (como o clique de um botão)
import javafx.fxml.FXML; // Anotação usada para vincular os atributos e métodos ao arquivo XML (cadastro-view.fxml)
import javafx.fxml.FXMLLoader; // Objeto responsável por carregar e inflar um novo arquivo FXML na memória
import javafx.scene.Scene; // Representa o container de conteúdo interno (a cena) de uma janela
import javafx.scene.control.*; // Importa os controles de interface (TextField, PasswordField, ComboBox, Button, Alert, etc.)
import javafx.stage.Stage; // Representa a janela física principal do aplicativo JavaFX

import java.io.IOException; // Exceção lançada caso ocorra uma falha ao ler os arquivos FXML do disco

public class CadastroController {

    // Atributos injetados do FXML que representam os campos de entrada de dados da tela de cadastro
    @FXML private TextField txtNome; // Campo de texto para o nome do aluno
    @FXML private TextField txtEmail; // Campo de texto para o e-mail do aluno
    @FXML private PasswordField txtSenha; // Campo mascarado para a senha do aluno
    @FXML private TextField txtMatricula; // Campo de texto para a matrícula acadêmica
    @FXML private ComboBox<String> comboCurso; // Caixa de seleção suspensa contendo os nomes dos cursos (Strings)
    @FXML private TextField txtTurma; // Campo de texto para a identificação da turma (ex: 3A)
    @FXML private Button btnCadastrar; // Botão que dispara a ação de submissão do formulário

    // Método de ciclo de vida do JavaFX executado automaticamente logo após o arquivo FXML ser carregado
    @FXML
    public void initialize() {
        // Acessa a lista de itens do ComboBox e popula com as opções de cursos disponíveis no sistema
        comboCurso.getItems().addAll(
                "Informática",
                "Redes de Computadores",
                "Eletrotécnica",
                "Mecânica",
                "Engenharia de Software"
        );
    }

    // Método disparado ao clicar no botão "Cadastrar" vinculado via onAction no FXML
    @FXML
    void realizarCadastro(ActionEvent event) {
        try {
            // Captura os textos digitados ou selecionados pelo usuário em cada componente da tela
            String nome = txtNome.getText();
            String email = txtEmail.getText();
            String senha = txtSenha.getText();
            String matricula = txtMatricula.getText();
            String curso = comboCurso.getValue(); // Pega o item atualmente selecionado no ComboBox
            String turma = txtTurma.getText();

            // Monta uma String contendo a estrutura de um objeto JSON usando blocos de texto do Java (Text Blocks)
            // Os marcadores %s servem como posições para injeção dinâmica de variáveis mais abaixo
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
                    // O método .formatted() substitui os %s na ordem exata, limpando caracteres perigosos via ApiClient.limpar()
                    ApiClient.limpar(nome),
                    ApiClient.limpar(email),
                    ApiClient.limpar(senha),
                    ApiClient.limpar(matricula),
                    ApiClient.limpar(turma),
                    ApiClient.limpar(curso)
            );

            // Envia o JSON estruturado para o endpoint "/Aluno" do backend através do método POST da sua classe ApiClient
            ApiClient.post("/Aluno", json);

            // Exibe uma caixa de diálogo informativa avisando o usuário sobre o sucesso da operação
            mostrarSucesso("Cadastro realizado com sucesso!");

            // Chama a rotina interna para fechar a tela atual e abrir a tela do painel principal (Dashboard)
            abrirDashboard();

        } catch (Exception e) {
            // Caso ocorra qualquer erro (seja de rede, campos nulos ou validação do banco), captura a exceção
            // Formata o erro de maneira legível e exibe uma caixa de diálogo com as instruções corretivas
            mostrarErro(formatarErroCadastro(e.getMessage()));
            e.printStackTrace(); // Imprime a pilha de erros no console da IDE para ajudar no diagnóstico técnico
        }
    }

    // Método privado responsável por fazer a transição de telas para o painel de controle
    private void abrirDashboard() {
        try {
            // Prepara o carregador apontando para o arquivo de visualização do painel geral (hello-view.fxml)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            // Carrega o arquivo FXML e o define como a raiz de uma nova cena gráfica
            Scene scene = new Scene(fxmlLoader.load());
            // Obtém a janela física atual (Stage) a partir do nó do botão de cadastro
            Stage stageAtual = (Stage) btnCadastrar.getScene().getWindow();

            // Instancia uma janela física completamente nova no sistema operacional
            Stage novoStage = new Stage();
            novoStage.setTitle("Displine - Dashboard"); // Define o título da barra superior da nova janela
            novoStage.setScene(scene); // Acopla a cena do dashboard dentro desta nova janela
            novoStage.setMaximized(true); // Configura a janela para iniciar em modo maximizado (tela cheia)
            novoStage.show(); // Exibe a nova janela na tela do usuário

            stageAtual.close(); // Fecha de forma definitiva a antiga janela de cadastro

        } catch (IOException e) {
            // Trata eventuais erros de leitura do arquivo FXML informando o usuário
            mostrarErro("Não foi possível abrir o dashboard.");
            e.printStackTrace(); // Registra o erro detalhado no console
        }
    }

    // Método disparado ao clicar no hyperlink "Já tenho uma conta" na interface gráfica
    @FXML
    void irParaLogin(ActionEvent event) {
        try {
            // Inicializa o carregador referenciando o arquivo da interface de autenticação (login-view.fxml)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            // Instancia uma nova cena contendo o layout de login carregado
            Scene scene = new Scene(fxmlLoader.load());
            // Reaproveita o Stage (janela) atual para não precisar abrir e fechar janelas piscando na tela
            Stage stage = (Stage) btnCadastrar.getScene().getWindow();

            stage.setTitle("Displine - Login"); // Modifica o título na borda da janela existente
            stage.setScene(scene); // Substitui o conteúdo atual da janela pela nova cena de login

        } catch (IOException e) {
            // Emite um alerta visual se o arquivo do login falhar em carregar
            mostrarErro("Não foi possível abrir a tela de login.");
            e.printStackTrace();
        }
    }

    // Cria e exibe uma janela modal nativa do tipo Informativa para mensagens positivas
    private void mostrarSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Define o tipo visual do alerta com ícone azul de informação
        alert.setTitle("Sucesso"); // Título da barra do alerta
        alert.setHeaderText("Operação realizada com sucesso"); // Cabeçalho interno em negrito
        alert.setContentText(mensagem); // Define o texto principal passado por parâmetro
        alert.showAndWait(); // Exibe a caixinha na tela e trava a interação com o app até que o usuário clique em "OK"
    }

    // Cria e exibe um alerta customizado do tipo Erro para listar as inconsistências do formulário
    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Configura o alerta com o ícone vermelho de erro
        alert.setTitle("Erro");
        alert.setHeaderText("Verifique os campos abaixo:");

        // Cria um componente Label em tempo de execução para comportar textos longos ou listas de erros
        Label label = new Label(mensagem);
        label.setWrapText(true); // Habilita a quebra automática de linha caso o texto chegue na extremidade direita
        label.setMaxWidth(400); // Define um limite de largura para o rótulo de texto

        // Substitui o painel de conteúdo padrão do Alert pelo nosso Label customizado
        alert.getDialogPane().setContent(label);
        alert.getDialogPane().setPrefWidth(480); // Ajusta a largura preferencial do diálogo de erro

        alert.showAndWait(); // Renderiza o alerta na tela aguardando a dispensa do usuário
    }

    // Método utilitário que analisa o texto de erro enviado pela API e o traduz em mensagens amigáveis para o usuário final
    private String formatarErroCadastro(String erroBruto) {
        // Validação preventiva: se o erro veio nulo ou em branco, devolve uma mensagem genérica padrão
        if (erroBruto == null || erroBruto.isBlank()) {
            return "Ocorreu um erro inesperado.";
        }

        // Instancia um manipulador eficiente de Strings para ir concatenando a lista de erros encontrados
        StringBuilder sb = new StringBuilder();

        // Estrutura de condições que verifica se palavras-chave específicas do backend constam na mensagem de erro bruta
        if (erroBruto.contains("nome")) {
            sb.append("• O nome é obrigatório.\n"); // Adiciona o marcador caso o nome tenha falhado nas validações
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

        // Verifica se a mensagem de erro diz respeito a violações de unicidade no banco de dados (chaves duplicadas)
        if (erroBruto.contains("duplicate")
                || erroBruto.contains("duplicar")
                || erroBruto.contains("unique")
                || erroBruto.contains("constraint")) {
            sb.append("• Já existe um cadastro com esse e-mail ou matrícula.\n");
        }

        // Se nenhuma palavra-chave conhecida foi identificada (o StringBuilder continua vazio)
        if (sb.isEmpty()) {
            // Retorna um informativo de falha genérica de preenchimento
            return "Não foi possível realizar o cadastro.\nVerifique os dados informados e tente novamente.";
        }

        // Converte o acumulador StringBuilder de volta para String limpa e o retorna para exibição no alerta
        return sb.toString();
    }
}