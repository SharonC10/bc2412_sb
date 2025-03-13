package com.ecommerceapp.simpleapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerceapp.simpleapp.entity.OrderEntity;
import com.ecommerceapp.simpleapp.entity.UserEntity;
import com.ecommerceapp.simpleapp.repository.OrderRepo;

@Service
public class OrderService {

  @Autowired
  private OrderRepo orderRepo;

  public List<OrderEntity> getAllOrder() {
    return orderRepo.findAll();
  }

  public OrderEntity getOrderById(Long id) {
    return orderRepo.findById(id).orElseThrow(
        () -> new RuntimeException("Order with id " + id + " not found"));
  }

  public void createOrder(OrderEntity order) {
    orderRepo.save(order);
  }

  public void updateOrder(OrderEntity order) {
    orderRepo.findById(order.getId()).orElseThrow(() -> new RuntimeException(
        "Order with id " + order.getId() + " not found"));
    orderRepo.save(order);
  }

  public void deleteOrder(Long id) {
    orderRepo.findById(id).orElseThrow(
        () -> new RuntimeException("Order with id " + id + " not found"));
    orderRepo.deleteById(id);
  }

  public List<OrderEntity> findOrdersByUser(UserEntity user) {
    return orderRepo.findByUser(user);
  }


}
