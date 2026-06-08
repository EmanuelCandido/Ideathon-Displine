package com.example.displine; // Declara o pacote (diretório) onde a classe está inserida

// Importações de classes nativas do Java para comunicação de rede, tratamento de erros e formatação de texto
import java.io.IOException; // Trata erros de entrada/saída, comuns quando o servidor está fora do ar ou a rede cai
import java.net.URI; // Representa e valida o endereço de uma URL (Uniform Resource Identifier)
import java.net.http.HttpClient; // O cliente HTTP principal responsável por gerenciar e enviar as requisições
import java.net.http.HttpRequest; // Representa a mensagem que vamos enviar (URL, cabeçalhos, método e corpo)
import java.net.http.HttpResponse; // Representa a resposta que volta do servidor (dados e código de status)
import java.nio.charset.StandardCharsets; // Garante o uso do padrão UTF-8 para evitar problemas com acentos e símbolos

public class ApiClient {

    // Define a URL base fixa do seu backend. Toda requisição começará com esse endereço
    private static final String BASE_URL = "http://localhost:8080";

    // Cria uma única instância compartilhada do cliente HTTP para ser reutilizada em toda a aplicação
    private static final HttpClient client = HttpClient.newHttpClient();

    // Método para cadastrar dados no backend (Verbo POST). Retorna a resposta da API como String
    public static String post(String endpoint, String json) throws IOException, InterruptedException {
        // Usa o padrão Builder para montar passo a passo a estrutura da requisição HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint)) // Junta a URL base com a rota específica (ex: http://localhost:8080/usuarios)
                .header("Content-Type", "application/json") // Informa no cabeçalho da requisição que os dados enviados estão no formato JSON
                .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8)) // Define o método como POST e insere o texto JSON codificado em UTF-8
                .build(); // Conclui o Builder e gera o objeto HttpRequest final

        // Envia a requisição através do cliente de forma síncrona e dita que a resposta deve ser guardada como uma String
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verifica se o código de status HTTP retornado indica um erro (400 ou maior, ex: 400 Bad Request, 404 Not Found, 500 Server Error)
        if (response.statusCode() >= 400) {
            // Dispara uma exceção parando a execução e exibindo o código do erro junto com a mensagem enviada pelo backend
            throw new RuntimeException("Erro " + response.statusCode() + ": " + response.body());
        }

        // Se deu tudo certo (código de sucesso como 200 ou 201), retorna o corpo da resposta contendo os dados do backend
        return response.body();
    }

    // Método para atualizar dados já existentes no backend (Verbo PUT)
    public static String put(String endpoint, String json) throws IOException, InterruptedException {
        // Inicia o construtor da requisição HTTP voltada para atualização
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint)) // Define a URL de destino com o parâmetro do recurso a ser modificado
                .header("Content-Type", "application/json") // Define o tipo de conteúdo como JSON
                .PUT(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8)) // Define o método como PUT e anexa o JSON com as modificações
                .build(); // Fabrica o objeto HttpRequest

        // Dispara o pacote de dados e captura a resposta textual retornada do servidor
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Valida se o status retornado pela API aponta para uma falha no processamento da requisição
        if (response.statusCode() >= 400) {
            // Interrompe o fluxo lançando a exceção com o código e corpo do erro
            throw new RuntimeException("Erro " + response.statusCode() + ": " + response.body());
        }

        // Retorna o corpo da resposta de sucesso enviada pelo servidor
        return response.body();
    }

    // Método para buscar ou listar informações do backend (Verbo GET)
    public static String get(String endpoint) throws IOException, InterruptedException {
        // Inicia a montagem de uma requisição de consulta
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint)) // Configura a URL de busca (ex: http://localhost:8080/compromissos)
                .GET() // Define o método HTTP expressamente como GET (este método não envia dados no corpo da requisição)
                .build(); // Cria a requisição

        // Envia o pedido ao backend e aguarda o retorno da String contendo os dados solicitados
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Checa se o status HTTP recebido indica alguma falha ou se a rota não foi encontrada
        if (response.statusCode() >= 400) {
            // Lança uma exceção contendo os detalhes do erro retornado
            throw new RuntimeException("Erro " + response.statusCode() + ": " + response.body());
        }

        // Devolve o texto da resposta (geralmente uma lista de objetos ou um objeto específico em formato JSON)
        return response.body();
    }

    // Método utilitário usado para higienizar textos e evitar quebras de sintaxe ao montar strings JSON na mão
    public static String limpar(String texto) {
        // Filtro de segurança: Se o texto passado for nulo, retorna uma string vazia para evitar erros do tipo NullPointerException
        if (texto == null) {
            return "";
        }

        // Substitui caracteres especiais: barras invertidas simples (\) viram duplas (\\) e aspas duplas (") viram aspas escapadas (\")
        // Isso garante que o texto digitado pelo usuário entre como um valor de texto puro, sem quebrar a estrutura de aspas do JSON
        return texto.replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}