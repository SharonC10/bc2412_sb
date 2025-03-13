package com.demospcotroller.serivce;

import java.util.List;
import com.demospcotroller.entity.PersonEntity;

public interface PersonService  {
  //boolean add(Person person);
  List<PersonEntity>getPerson();
  PersonEntity createPerson(PersonEntity personEntity);
  //List<PersonEntity> findPersonByName();
  List<PersonEntity> findByNameByJPQL(String personName);
  List<PersonEntity> findByNameByNativeQuery(String personName);
}
