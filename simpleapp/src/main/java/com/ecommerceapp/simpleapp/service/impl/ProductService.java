package com.ecommerceapp.simpleapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerceapp.simpleapp.entity.ProductEntity;
import com.ecommerceapp.simpleapp.repository.ProductRepo;

@Service
public class ProductService {
  @Autowired
  private ProductRepo productRepo;

  public List<ProductEntity> getAllProduct() {
    return productRepo.findAll();
  }

  public ProductEntity getProductById(Long id) {
    return productRepo.findById(id).orElseThrow(
        () -> new RuntimeException("Product with id " + id + " not found"));
  }

  public void createProduct(ProductEntity Product) {
    productRepo.save(Product);
  }

  public void updateProduct(ProductEntity Product) {
    productRepo.findById(Product.getId())
        .orElseThrow(() -> new RuntimeException(
            "Product with id " + Product.getId() + " not found"));
    productRepo.save(Product);
  }

  public void deleteProduct(Long id) {
    productRepo.findById(id).orElseThrow(
        () -> new RuntimeException("Product with id " + id + " not found"));
    productRepo.deleteById(id);
  }

  public ProductEntity findProductByName(String name) {
    return productRepo.findByName(name);
  }


}
