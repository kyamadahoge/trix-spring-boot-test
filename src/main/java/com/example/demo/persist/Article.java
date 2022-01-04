package com.example.demo.persist;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Entity
@Data
public class Article {
  @Id
  private Integer id;
  @Lob
  private String content;
}