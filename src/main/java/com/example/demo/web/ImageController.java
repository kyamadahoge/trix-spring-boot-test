package com.example.demo.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.persist.Image;
import com.example.demo.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ImageController {
  
  @Autowired
  private ImageService service;

  @GetMapping("/image/{filename}")
  public ResponseEntity<byte[]> get(@PathVariable(name="filename") String filename) {
    Image image = service.getImage(filename);
    if (image == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", image.getContentType());

    return new ResponseEntity<>(image.getBin(), headers, HttpStatus.OK);
  }

  @PostMapping("/image/put")
  @ResponseBody
  public ResponseEntity<Map<String, String>> put(String contentType, MultipartFile file) throws IOException {
    String filename = service.putImage(contentType, file.getBytes());

    Map<String, String> content = new HashMap<>();
    content.put("url", "/image/" + filename);
    return new ResponseEntity<>(content, new HttpHeaders(), HttpStatus.CREATED);
  }
}
