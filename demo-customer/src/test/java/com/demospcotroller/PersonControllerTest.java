package com.demospcotroller;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.demospcotroller.codewave.ApiResp;
import com.demospcotroller.controller.impl.PersonController;
import com.demospcotroller.entity.PersonEntity;
import com.demospcotroller.serivce.PersonService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

// Every test file is a testing env, with your terget bean cycle.
// Spring Boot test -> call all the env Bean
// Situation: Only Test Controller
// ! For Unit Test, we don't need [service & repository layer(bean)].
@WebMvcTest(controllers = PersonController.class) // Context includes all web related beans ONLY
// Specific the PersonController Class
class PersonControllerTest { // not share
  @MockBean
  private PersonService personService;

  @Autowired // ! @WebMvcTest inject MockMvc Bean into context
  private MockMvc mockMvc; // ! pretend Postman

  @Test
  void testGetAllPersons() throws Exception{
    //Mock behavior for the mock bean
    PersonEntity personEntity = PersonEntity.builder()
        .email("test123@gmail.com").name("testname1").id(99L).build(); //
    PersonEntity personEntity2 = PersonEntity.builder()
        .email("test234@gmail.com").name("testname2").id(999L).build();

    List<PersonEntity> serviceResult =
        Arrays.asList(personEntity, personEntity2);
//----------------------------------------------------------------

    // Assume the behavior /result for the mock bean
    Mockito.when(personService.getPerson()).thenReturn(serviceResult);
    //! expect result get the List <PersonEntity>

    // Test
    // Verify result
    ResultActions result = mockMvc.perform(get("/persons"));
    result.andExpect(status().isOk())
    .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    
    //Vincent not really like to use 
    //To check the data.
    //! Approrach 1
    //! 1. insert the data-----------------------------
    result.andExpect(jsonPath("$.code").value("000000"))
        .andExpect(jsonPath("$.message").value("Success."))
        .andExpect(jsonPath("$.data[0].name").value("testname1"))
        .andExpect(jsonPath("$.data[0].email").value("test123@gmail.com"));
//------------------------------------------------------------------------------
    
//! Approach2
    String json = result.andReturn().getResponse().getContentAsString();
    // Json -> Object(deserialization)
    ApiResp<List<PersonEntity>>personEntityArray = new ObjectMapper() //! Json -> Object(deserialization)
        .readValue(json, new TypeReference<ApiResp<List<PersonEntity>>>() {});

    MatcherAssert.assertThat(personEntityArray.getCode(),Matchers.is("000000"));
    MatcherAssert.assertThat(personEntityArray.getMessage(), Matchers.is("Success."));

    List<PersonEntity> personEntities = personEntityArray.getData();

    MatcherAssert.assertThat(personEntities, Matchers.containsInAnyOrder(
      Matchers.hasProperty("email", Matchers.equalTo("test123@gmail.com")),
      Matchers.hasProperty("email",Matchers.equalTo("test234@gmail.com"))
    ));
  }
    

    //--------------------------------------------------------------------------
  @Test //Test Api , Test create person or not , test the data include email or not??? 
    void testCreatePerson() throws Exception{ 
      //! Mock Behavior (Pass Mary, return John)
      PersonEntity personEntity3= PersonEntity.builder()
      .email("sharon@gamil.com")
      .name("Sharon")
      .id(888L)
      .build();

      PersonEntity personEntityRequest= PersonEntity.builder()
      .email("mary@gamil.com")
      .name("Mary")
      .id(10L)
      .build();

      Mockito.when(personService.createPerson(personEntity3)).thenReturn(personEntityRequest);
      //! Expect result: email:sharonc@gmail.com name: Sharon .....

      //! Prepare Request Body (Mary)
      // Serialization
      String requestBodyJson = 
      new ObjectMapper().writeValueAsString(personEntityRequest);

      ResultActions result2 = mockMvc//
      .perform(post("/person")//
      .contentType(MediaType.APPLICATION_JSON_VALUE)//
      .content(requestBodyJson))//
      .andExpect(status().isCreated())//
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));

      String json2 = result2.andReturn().getResponse().getContentAsString();

      //Deserialization
      //! KEY!!!
      ApiResp<PersonEntity> personEntityObject = new ObjectMapper()
      .readValue(json2,  //! Json -> Object(deserialization)
            new TypeReference<ApiResp<PersonEntity>>() {});

    MatcherAssert.assertThat(personEntityObject.getCode(),Matchers.is("000000"));
    MatcherAssert.assertThat(personEntityObject.getMessage(), Matchers.is("Success."));

    PersonEntity responseData = personEntityObject.getData();

    MatcherAssert.assertThat(responseData.getName(), Matchers.is("Sharon"));

    verify(personService, times(1)).createPerson(personEntityRequest);
  }

}
