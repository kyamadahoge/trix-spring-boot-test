package com.example.demo.web;

import com.example.demo.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @GetMapping(value = {"/", "/show"})
  public ModelAndView show() {
    String content = articleService.getContent();
    ModelAndView mav = new ModelAndView("show");
    mav.addObject("content", content);
    return mav;
  }

  @GetMapping("/edit")
  public ModelAndView edit() {
    String content = articleService.getContent();
    ArticleForm form = new ArticleForm();
    form.setContent(content);
    ModelAndView mav = new ModelAndView("edit");
    mav.addObject("form", form);
    return mav;
  }

  @PostMapping("/save")
  public ModelAndView save(@ModelAttribute ArticleForm form) {
    String content = form.getContent();
    articleService.putContent(content);
    return new ModelAndView("redirect:/show");
  }
}
