package com.ecommerceapp.simpleapp.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerceapp.simpleapp.entity.ProductEntity;
import com.ecommerceapp.simpleapp.service.impl.ProductService;

@Controller
public class ProductController {
  @Autowired
  private ProductService productService;


  public String addProduct(ProductEntity product) {
    productService.createProduct(product);

    return "redirect:/admin/home";
  }


  public String updateProduct(Long id, Model model) {
    model.addAttribute("product", productService.getProductById(id));

    return "UpdateProduct";
  }

  public String updateProduct(ProductEntity product) {
    productService.updateProduct(product);

    return "redirect:/admin/home";
  }


  public String deleteProduct(Long id) {
    productService.deleteProduct(id);

    return "redirect:/admin/home";
  }


}
