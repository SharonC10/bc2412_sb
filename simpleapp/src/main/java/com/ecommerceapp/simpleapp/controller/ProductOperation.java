package com.ecommerceapp.simpleapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.ecommerceapp.simpleapp.entity.ProductEntity;

public interface ProductOperation {

  @PostMapping("/add/product")
  public String addProduct(ProductEntity product);

  @GetMapping("/update/product/{id}")
  public String updateProduct(@PathVariable Long id, Model model);

  @PostMapping("/update/product")
  public String updateProduct(ProductEntity product);

  @GetMapping("/delete/product/{id}")
  public String deleteProduct(@PathVariable Long id);
}
