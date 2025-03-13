package com.ecommerceapp.simpleapp.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerceapp.simpleapp.controller.HomeOperation;
import com.ecommerceapp.simpleapp.entity.AdminEntity;
import com.ecommerceapp.simpleapp.entity.MessageEntity;
import com.ecommerceapp.simpleapp.service.impl.ProductService;

@Controller
public class HomeController implements HomeOperation {

  @Autowired
  private ProductService productService;

  @Override
  public String homePage() {
    return "HomePage";
  }


  @Override
  public String productPage(Model model) {
    model.addAttribute("productList", productService.getAllProduct());

    return "Products";
  }

  @Override
  public String contactPage(Model model) {
    model.addAttribute("message", new MessageEntity());

    return "ContactUs";
  }


  @Override
  public String aboutUs() {
    return "AboutUs";
  }


  @Override
  public String login(Model model) {
    model.addAttribute("admin", new AdminEntity());
    return "LoginPage";
  }

 

}
