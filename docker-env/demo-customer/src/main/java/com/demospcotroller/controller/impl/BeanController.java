package com.demospcotroller.controller.impl;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class BeanController {
  @Autowired
  private String abc;
  
  @GetMapping(value = "/bean/tutor")
  public String getTutor(){
    return abc;
  }

  
  
}
