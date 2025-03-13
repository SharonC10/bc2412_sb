package com.demospcotroller.serivce.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demospcotroller.codewave.BusinessException;
import com.demospcotroller.codewave.SysCode;
import com.demospcotroller.entity.OrderEntity;
import com.demospcotroller.entity.PersonEntity;
import com.demospcotroller.repository.OrderRepository;
import com.demospcotroller.repository.PersonRepository;
import com.demospcotroller.serivce.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private PersonRepository personRepository;
  
  @Override
  //throw customer id not found , throw new BusinessException ("Person Id not found.")
  public OrderEntity createOrder(Long customersId, OrderEntity orderEntity){ //收customerId and Order資料
    PersonEntity personEntity = this.personRepository.findById(customersId)
    .orElseThrow(() -> BusinessException.of(SysCode.ID_NOT_FOUND));
    orderEntity.setPersonEntity(personEntity);
    //Save orderEntity to DB (Database)
    return this.orderRepository.save(orderEntity);
  };

  @Override
  public OrderEntity deleteOrder(Long orderId){
    OrderEntity orderEntity = this.orderRepository.findById(orderId)
    .orElseThrow(() -> BusinessException.of(SysCode.ID_NOT_FOUND));
    OrderEntity deletedOrderEntity = orderEntity;
    
    this.orderRepository.delete(orderEntity);

    return deletedOrderEntity;
  };
}
