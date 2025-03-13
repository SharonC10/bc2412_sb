package com.demospcotroller.serivce.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demospcotroller.entity.PersonEntity;
import com.demospcotroller.model.Person;
import com.demospcotroller.repository.PersonRepository;
import com.demospcotroller.serivce.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
  @Autowired
  private PersonRepository personRepository;

  @Override 
  public List<PersonEntity>getPerson(){
    return this.personRepository.findAll(); //john 20 , mary 40
  }

  @Override
  public PersonEntity createPerson(PersonEntity personEntity){
    return this.personRepository.save(personEntity);
  }

  // @Override (NEED TO COMPELETE)
  // public List<PersonEntity> findPersonByName(){
    
  //   return this.personRepository.findByName();
  // }

  // @Override
  // public boolean add(Person person){
  //   for (int i =0; i < Database.STOREPERSON.length; i++){
  //     if (Database.STOREPERSON[i] == null){
  //       Database.STOREPERSON[i] = person;
  //     }
  //   }
  //   return false; 
  // }

  @Override 
  public List<PersonEntity> findByNameByJPQL(String personName){
return this.personRepository.findByNameByJPQL(personName);
  }

  @Override 
  public List<PersonEntity> findByNameByNativeQuery(String personName){
return this.personRepository.findByNameByNativeQuery(personName);
  }

  
  
}
