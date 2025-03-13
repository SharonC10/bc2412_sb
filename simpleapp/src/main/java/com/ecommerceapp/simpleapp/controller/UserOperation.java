package com.ecommerceapp.simpleapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.ecommerceapp.simpleapp.entity.UserEntity;

public interface UserOperation {

  @GetMapping("/update/user/{id}")
  String updateUser(@PathVariable("id") Long id, Model model);

  @PostMapping("/add/user")
  String addUser(UserEntity user);

  @PostMapping("/update/user")
  String updateUser(UserEntity user);


  @GetMapping("/delete/user/{id}")
  String deleteUser(@PathVariable Long id);

}
