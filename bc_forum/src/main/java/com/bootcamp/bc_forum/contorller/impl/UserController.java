package com.bootcamp.bc_forum.contorller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_forum.contorller.UserOperation;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.model.dto.UserDto;
import com.bootcamp.bc_forum.service.UserService;

@RestController
public class UserController implements UserOperation{
  @Autowired
  private UserService userService;
  
  @Override
  public List<UserDTO> getUsers(){
    return this.userService.getUsers();
  }
  
}
