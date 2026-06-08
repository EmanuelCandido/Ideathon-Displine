package com.example.displine; // Declaração do pacote (diretório) onde esta classe de gerenciamento de sessão está localizada

// Definição da classe SessaoUsuario
// Esta classe funciona como um contêiner global para armazenar os dados do usuário que está logado no momento.
// Por usar membros estáticos, ela elimina a necessidade de instanciar objetos ou passar dados de tela em tela manualmente.
public class SessaoUsuario {

    // ATRIBUTOS PRIVADOS E ESTÁTICOS (static)
    // A palavra-chave 'static' faz com que essas variáveis pertençam à classe, e não a uma instância (objeto) dela.
    // Isso significa que existe apenas uma cópia dessas variáveis na memória de todo o aplicativo.
    private static Long idUsuario; // Armazena de forma global o identificador único (ID) do usuário logado
    private static String nome; // Armazena de forma global o nome de exibição do usuário logado
    private static String email; // Armazena de forma global o endereço de e-mail do usuário logado

    // MÉTODOS GETTERS E SETTERS ESTÁTICOS
    // Como os atributos são estáticos e privados, os métodos para acessá-los e modificá-los também precisam ser estáticos.

    // Método público que permite qualquer tela do sistema ler o ID do usuário atualmente conectado
    public static Long getIdUsuario() {
        return idUsuario;
    }

    // Método público que define ou atualiza o ID do usuário logado (geralmente chamado logo após o sucesso do login)
    public static void setIdUsuario(Long idUsuario) {
        // Usa-se 'NomeDaClasse.atributo' para explicitar que estamos alterando a variável estática da classe
        SessaoUsuario.idUsuario = idUsuario;
    }

    // Método público que permite recuperar o nome do usuário ativo (ex: para exibir na saudação do Dashboard)
    public static String getNome() {
        return nome;
    }

    // Método público que armazena ou altera o nome do usuário logado na sessão global
    public static void setNome(String nome) {
        SessaoUsuario.nome = nome;
    }

    // Método público que permite consultar o e-mail do usuário em qualquer parte do sistema
    public static String getEmail() {
        return email;
    }

    // Método público que armazena ou altera o e-mail do usuário logado na sessão global
    public static void setEmail(String email) {
        SessaoUsuario.email = email;
    }
}