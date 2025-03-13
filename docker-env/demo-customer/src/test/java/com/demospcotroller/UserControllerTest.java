package com.demospcotroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.demospcotroller.controller.impl.UserController;
import com.demospcotroller.dto.UserDTO;
import com.demospcotroller.dto.mapper.UserDTOMapper;
import com.demospcotroller.entity.UserEntity;
import com.demospcotroller.model.dto.UserDto;
import com.demospcotroller.serivce.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = UserController.class)

class UserControllerTest {
  @MockBean
  private UserService userService; // 10 method, mock 10 method

  @SpyBean
  private UserDTOMapper userDTOMapper; // 10 method , real 10 method, still mock method 
  
  @Autowired
  private MockMvc mockMvc;

  @Test
  void testGetUsers() throws Exception {
    // John
    UserDto.Address.Geo johnGeo = UserDto.Address.Geo.builder().build();
    UserDto.Address jognAddress =
      UserDto.Address.builder().geo(johnGeo).build();
    UserDto.Company johCompany = UserDto.Company.builder().build();
    UserDto john = UserDto.builder().name("John").address(jognAddress)
    .company(johCompany).build();

    //Mary
    UserDto.Address.Geo maryGeo = UserDto.Address.Geo.builder().build();
    UserDto.Address maryAddress = 
    UserDto.Address.builder().geo(maryGeo).build();
    UserDto.Company maryCompany = UserDto.Company.builder().build();
    UserDto mary = UserDto.builder().name("Mary").address(maryAddress)
    .company(maryCompany).build();

    List<UserDto> userDtos = Arrays.asList(john,mary);

    Mockito.when(userService.getUsers()).thenReturn(userDtos);

    ResultActions result = mockMvc.perform(get("/jsonplaceholder/users"));
    result.andExpect(status().isOk())
    .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    // To check the data
    String json = result.andReturn().getResponse().getContentAsString();
    List<UserDTO> userDTOs = new ObjectMapper().readValue(json, new TypeReference
      <List<UserDTO>>(){});

      MatcherAssert.assertThat(userDTOs, Matchers.containsInAnyOrder(
        Matchers.hasProperty("name", Matchers.equalTo("John")),
        Matchers.hasProperty("name", Matchers.equalTo("Mary"))
      ));
  }
}
