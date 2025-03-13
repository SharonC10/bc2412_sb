package com.demospcotroller.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.demospcotroller.codewave.ApiResp;
import com.demospcotroller.codewave.SysCode;
import com.demospcotroller.controller.PersonOperation;
import com.demospcotroller.entity.PersonEntity;
import com.demospcotroller.serivce.PersonService;

@RestController
public class PersonController implements PersonOperation {

  @Autowired
  private PersonService personService;

  // @Override
  // public Person addPerson( Person person){
  // if (this.personService.add(person))
  // return person;
  // return null;
  // }

  @Override
  public ApiResp<List<PersonEntity>> getPerson() {
    List<PersonEntity> personEntity = this.personService.getPerson();
    return ApiResp.<List<PersonEntity>>builder()//
    .syscode(SysCode.OK)
        .data(personEntity)
        .build();
  }

  @Override
  public ApiResp<PersonEntity> createPerson(PersonEntity personEntity) {
    PersonEntity serviceResult = this.personService.createPerson(personEntity);
    return ApiResp.<PersonEntity>builder()//
    .syscode(SysCode.OK)
    .data(serviceResult)
    .build();
  }
}
