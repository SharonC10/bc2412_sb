package com.ecommerceapp.simpleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ecommerceapp.simpleapp.entity.ProductEntity;
import jakarta.transaction.Transactional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

  public ProductEntity findByName(String name);

  @Transactional
  @Query(
      value = "SELECT setval(products_id_seq, (SELECT MAX(id) FROM products))",
      nativeQuery = true)
  void resetUserSequence();

}
