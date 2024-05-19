package com.weslleycoelho.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weslleycoelho.workshopmongo.domain.Post;
import com.weslleycoelho.workshopmongo.repositories.PostRepository;
import com.weslleycoelho.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

  @Autowired
  private PostRepository repository;

  public Post findById(String id) {
    Optional<Post> post = repository.findById(id);
    return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
  }

  public List<Post> findByTitle(String text) {
    return repository.findByTitleContainingIgnoreCase(text);
  }

  public List<Post> fullSearch(String text, Date min, Date max) {
    max = new Date(max.getTime() + 24 * 60 * 60 * 1000);
    return repository.fullSearch(text, min, max);
  }

}
