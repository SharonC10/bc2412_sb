package com.demospcotroller.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.demospcotroller.codewave.ApiResp;
import com.demospcotroller.codewave.SysCode;
import com.demospcotroller.controller.OrderOperation;
import com.demospcotroller.entity.OrderEntity;
import com.demospcotroller.entity.PersonEntity;
import com.demospcotroller.serivce.OrderService;

@RestController
public class OrderController implements OrderOperation{
  @Autowired
  private OrderService orderService;

  @Override
  public ApiResp<OrderEntity> createOrder(Long customersId, OrderEntity orderEntity){
    OrderEntity create = //
    this.orderService.createOrder(customersId, orderEntity);
    return ApiResp.<OrderEntity>builder()//
    .syscode(SysCode.OK)
    .build() ;
  }

  @Override
  public ApiResp<OrderEntity> deleteOrderById(Long orderId ){
    OrderEntity serviceResult =
        this.orderService.deleteOrder(orderId);
    return ApiResp.<OrderEntity>builder() //
        .syscode(SysCode.OK) //
        .build();
  }
}
