package com.demospcotroller.serivce;

import com.demospcotroller.entity.OrderEntity;

public interface OrderService {

  //throw customer id not found , throw new BusinessException ("Person Id not found.")
  OrderEntity createOrder(Long customersId, OrderEntity orderEntity);
  OrderEntity deleteOrder(Long orderId);
}
