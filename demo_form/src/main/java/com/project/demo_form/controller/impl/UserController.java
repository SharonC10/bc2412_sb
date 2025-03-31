package com.project.demo_form.controller.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.demo_form.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class UserController {
  @GetMapping("/register")
  public String getFormPage(Model model) {
    User user = new User();
    model.addAttribute("user", user);

    List<Character> genderList = Arrays.asList(' ','M','F');
    model.addAttribute("genderList", genderList);

          return "RegisterForm";
  }
  
  @PostMapping("/register")
  public String createForm(@ModelAttribute("user") User user) {
      System.out.println(user);
      
      return "RegisterSuccess";
  }
  
}
