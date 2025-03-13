package com.demospcotroller.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.demospcotroller.codewave.ApiResp;
import com.demospcotroller.entity.PersonEntity;

public interface PersonOperation {
  // @PostMapping(value = "/person")
  // Person addPerson(@RequestBody Person person);
  // @PostMapping (value = "customer")
  // CustomerEntity createCustomer

  @GetMapping (value = "/persons")
 
  ApiResp<List<PersonEntity>> getPerson();

  @PostMapping (value = "/person")
  ApiResp<PersonEntity> createPerson(@RequestBody PersonEntity personEntity);
  
  
}
