package com.example.demo.service;

import java.util.UUID;

import javax.transaction.Transactional;

import com.example.demo.persist.Image;
import com.example.demo.persist.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
  
  @Autowired
  private ImageRepository repository;

  public Image getImage(String filename) {
    return repository.findByFilenameEquals(filename);
  }

  @Transactional
  public String putImage(String contentType, byte[] bin) {
    Image image = new Image();
    String uuid = UUID.randomUUID().toString();
    image.setFilename(uuid);
    image.setContentType(contentType);
    image.setBin(bin);
    repository.save(image);
    return uuid;
  }
}
