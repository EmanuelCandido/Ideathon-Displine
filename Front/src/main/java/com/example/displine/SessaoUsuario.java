package com.example.displine;

public class SessaoUsuario {

    private static Long idUsuario;
    private static String nome;
    private static String email;

    public static Long getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(Long idUsuario) {
        SessaoUsuario.idUsuario = idUsuario;
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        SessaoUsuario.nome = nome;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SessaoUsuario.email = email;
    }
}