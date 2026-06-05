package com.sistemaIdeathon.displine.DTO;

import java.time.LocalDate;

import com.sistemaIdeathon.displine.entity.Post;

public class PostDTO {

	private Long idPost;

    private String titulo;

    private String conteudo;

    private LocalDate dataPublicacao;

    private Long idUsuario;

    public PostDTO(Post post) {
        this.idPost = post.getIdPost();
        this.titulo = post.getTitulo();
        this.conteudo = post.getConteudo();
        this.dataPublicacao = post.getDataPublicacao();

        this.idUsuario = post.getUsuario() != null
                ? post.getUsuario().getIdUsuario()
                : null;
	
}
}
