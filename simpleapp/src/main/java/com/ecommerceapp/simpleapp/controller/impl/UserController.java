package com.ecommerceapp.simpleapp.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerceapp.simpleapp.controller.UserOperation;
import com.ecommerceapp.simpleapp.entity.UserEntity;
import com.ecommerceapp.simpleapp.service.impl.UserService;

@Controller
public class UserController implements UserOperation {
  @Autowired
  private UserService userService;


  public String updateUser(Long id, Model model) {
    model.addAttribute("user", userService.getUserById(id));

    return "UpdateUser";
  }

  public String addUser(UserEntity user) {
    userService.createUser(user);

    return "LoginPage";
  }


  public String updateUser(UserEntity user) {
    userService.createUser(user);

    return "redirect:/admin/home";
  }


  public String deleteUser(Long id) {
    userService.deleteUser(id);

    return "redirect:/admin/home";
  }


}
