package com.ecommerceapp.simpleapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ecommerceapp.simpleapp.entity.AdminEntity;
import com.ecommerceapp.simpleapp.entity.OrderEntity;
import com.ecommerceapp.simpleapp.entity.UserEntity;

public interface AdminOperation {

//   @GetMapping("/admin/verify/credentials")
//   String verifyCredentials(@ModelAttribute("admin") AdminEntity admin,
//       Model model);

//   @GetMapping("/admin/home")
//   String adminHomePage(Model model);

  @PostMapping("/add/admin")
  String createAdmin(AdminEntity admin);

  @GetMapping("/update/admin/{id}")
  String update(@PathVariable("id") Long id, Model model);

  @PostMapping("/update/admin")
  String updateAdmin(AdminEntity admin);

  @GetMapping("/delete/admin/{id}")
  String deleteAdmin(@PathVariable Long id);


  @GetMapping("/user/home")
  String userHome(@ModelAttribute("userId") Long userId,
      @ModelAttribute("error") String error,
      @ModelAttribute("messageSuccess") String messageSuccess, Model model);


  @PostMapping("/user/login")
  String userLogin(UserEntity user, RedirectAttributes redirectAttributes);

  @PostMapping("/product/search")
  String productSearch(String name, Long userId, Model model);

  @PostMapping("/place/order")
  public String placeOrder(OrderEntity order, Long userId,
      RedirectAttributes redirectAttributes);


}

