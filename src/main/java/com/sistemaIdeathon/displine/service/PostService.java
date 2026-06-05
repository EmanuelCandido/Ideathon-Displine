package com.sistemaIdeathon.displine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaIdeathon.displine.DTO.PostDTO;
import com.sistemaIdeathon.displine.entity.Post;
import com.sistemaIdeathon.displine.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository repository;
	
	public List<PostDTO> listar() {
		return repository.findAll().stream().map(PostDTO::new).toList();
		}
	
	public Post salvar(Post post) {
		return repository.save(post);
	}
	
	public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Post atualizar(Long id, Post post) {

    	post.setIdPost(id);

        return repository.save(post);
    }
	

}
