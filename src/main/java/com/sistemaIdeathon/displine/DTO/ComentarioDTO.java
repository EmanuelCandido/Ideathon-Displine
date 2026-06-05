package com.sistemaIdeathon.displine.DTO;

import java.time.LocalDate;

import com.sistemaIdeathon.displine.entity.Comentario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ComentarioDTO {

	private Long idComentario;

    private String conteudo;

    private LocalDate dataComentario;

    private Long idPost;

    private Long idUsuario;

    public ComentarioDTO(Comentario comentario) {
        this.idComentario = comentario.getIdComentario();
        this.conteudo = comentario.getConteudo();
        this.dataComentario = comentario.getDataComentario();

        this.idPost = comentario.getPost() != null 
                ? comentario.getPost().getIdPost() 
                : null;

        this.idUsuario = comentario.getUsuario() != null 
                ? comentario.getUsuario().getIdUsuario() 
                : null;
    }
}

