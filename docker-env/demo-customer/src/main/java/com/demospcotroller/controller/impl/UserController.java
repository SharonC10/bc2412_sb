package com.demospcotroller.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demospcotroller.codewave.GlobalExceptionHandler;
import com.demospcotroller.controller.UserOperation;
import com.demospcotroller.dto.UserDTO;
import com.demospcotroller.dto.mapper.UserDTOMapper;
import com.demospcotroller.model.dto.UserDto;
import com.demospcotroller.serivce.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;


@RestController
public class UserController {// implements UserOperation {
  @Autowired
  private UserService userService;
  @Autowired
  private UserDTOMapper userDTOMapper;

  @GetMapping(value = "/jsonplaceholder/users")
  public List<UserDTO> getUsers() throws JsonProcessingException {
    // List of UserDto -> List of UserDTO
    List<UserDto> userDtos = this.userService.getUsers();
    System.out.println("userDtos=" + userDtos);
    return userDtos.stream() //
        .map(e -> userDTOMapper.map(e)) //
        .collect(Collectors.toList());
    // List<UserDto> serviceResults = this.userService.getUsers();
    // List<UserDTO> userDTOs = new ArrayList<>();
    // for (UserDto dto : serviceResults) {
    // UserDTO userDTO = userDTOMapper.map(dto);
    // userDTOs.add(userDTO);
    // }
    // return userDTOs;
  }

}
