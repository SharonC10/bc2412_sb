package com.ecommerceapp.simpleapp.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerceapp.simpleapp.service.impl.OrderService;

@Controller
public class OrderController {

  @Autowired
  private OrderService orderService;



}
