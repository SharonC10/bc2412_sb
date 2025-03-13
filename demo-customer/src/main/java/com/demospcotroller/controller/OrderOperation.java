package com.demospcotroller.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.demospcotroller.codewave.ApiResp;
import com.demospcotroller.entity.OrderEntity;

public interface OrderOperation {
  @PostMapping (value = "/order/{cid}")
  ApiResp<OrderEntity> createOrder(@PathVariable (value = "cid")Long customersId, @RequestBody OrderEntity orderEntity);
  
  @DeleteMapping (value = "/order/{orderId}")
  ApiResp<OrderEntity> deleteOrderById(@PathVariable Long orderId);
}
