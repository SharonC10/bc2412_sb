package com.demospcotroller;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.demospcotroller.entity.PersonEntity;
import com.demospcotroller.repository.PersonRepository;
import com.demospcotroller.serivce.PersonService;

@SpringBootTest //起env 齊Bean
public class PersonServiceTest {
  @Autowired 
  private PersonService personService;

  @MockBean //剝走repository
  private PersonRepository personRepository;

  
  @Test 
  //1. set the mock data for test
  void testGetAllPersons(){
    PersonEntity personEntity = PersonEntity.builder()
        .email("john@gmail.com").name("john").id(1L).build(); //
    PersonEntity personEntity2 = PersonEntity.builder()
        .email("mary@gmail.com").name("mary").id(2L).build();

    List<PersonEntity> dbResult =
        Arrays.asList(personEntity, personEntity2); //2. in this case: it will show list 
        // so create a List<>()

        //3. test what? 
        //! test the repository --> can it findAll() --> find the data?
        Mockito.when(personRepository.findAll()).thenReturn(dbResult);

        //4. Service call getPerson()
        List<PersonEntity> personEntities = personService.getPerson();
        //5. Marcher --> containsInAnyOrder (Require Both data)
        //!name
        MatcherAssert.assertThat(personEntities, Matchers.containsInAnyOrder(
      Matchers.hasProperty("name", Matchers.equalTo("john")),
      Matchers.hasProperty("name",Matchers.equalTo("mary"))));
        // !id 
      MatcherAssert.assertThat(personEntities, Matchers.containsInAnyOrder(
      Matchers.hasProperty("id", Matchers.equalTo(1L)),
      Matchers.hasProperty("id",Matchers.equalTo(2L))));
      // !email
      MatcherAssert.assertThat(personEntities, Matchers.containsInAnyOrder(
      Matchers.hasProperty("email", Matchers.equalTo("john@gmail.com")),
      Matchers.hasProperty("email",Matchers.equalTo("mary@gmail.com"))));
  }

}
