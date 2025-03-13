package com.ecommerceapp.simpleapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerceapp.simpleapp.entity.OrderEntity;
import com.ecommerceapp.simpleapp.entity.UserEntity;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Long> {

  public List<OrderEntity> findByUser(UserEntity user);

}
