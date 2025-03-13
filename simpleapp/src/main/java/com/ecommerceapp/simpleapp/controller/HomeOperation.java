package com.ecommerceapp.simpleapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface HomeOperation {

  @GetMapping({"/", "/home"})
  String homePage();


  @GetMapping("/products")
  public String productPage(Model model);

  @GetMapping("/contactUs")
  public String contactPage(Model model);

  @GetMapping("/aboutUs")
  public String aboutUs();


  @GetMapping("/login")
  public String login(Model model);
}
