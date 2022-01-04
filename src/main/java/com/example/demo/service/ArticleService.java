package com.example.demo.service;

import java.util.Optional;

import com.example.demo.persist.Article;
import com.example.demo.persist.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {

  @Autowired
  private ArticleRepository repository;

  private static final int ID = 1;

  public String getContent() {
    Optional<Article> entity = repository.findById(ID);
    return entity.isPresent()
      ? entity.get().getContent()
      : "";
  }

  @Transactional
  public void putContent(String content) {
    Optional<Article> entity = repository.findById(ID);
    Article article = null;
    if (entity.isPresent()) {
      article = entity.get();
    } else {
      article = new Article();
      article.setId(ID);
    }
    article.setContent(content);
    repository.save(article);
  }
}
