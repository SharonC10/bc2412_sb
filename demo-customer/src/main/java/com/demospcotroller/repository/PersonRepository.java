package com.demospcotroller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demospcotroller.entity.PersonEntity;
import com.demospcotroller.model.Person;
import java.util.List;
import java.util.Optional;

// JPA + Driver 
//! Hibernate generates the implementation class, which implememnts " CustomerRepository"
@Repository //bean
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
  //save()
  // saveAll()
  //findAll()

  //select * from Person where name = 'John'
  //Controller -> service 
  Optional <PersonEntity> findByName(String name);
  //Why Optional ? --> could be nothing
  //! How about return List?

  //support both and & or
  //List<PersonEntity> findByName(String Name);
  List<PersonEntity> findByNameAndEmail(String name, String email);

  //!JPQL -> Java program query languger.
  // Entity
  @Query(value = "select p from PersonEntity p where p.name = :name", nativeQuery = false)
  List<PersonEntity> findByNameByJPQL(@Param ("name") String personName); 

  //! Native Query
  @Query(value = "select p.* from person p where p.customer_name = :name", nativeQuery = true)
  List<PersonEntity> findByNameByNativeQuery(//
    @Param ("name") String personName);
  //! p.customer_name ---> 要對PersonEntity filname (id, customer_name, customer_email)
}
// What is Respository? 
// = SQL select all...insert into...search by id....find all....
