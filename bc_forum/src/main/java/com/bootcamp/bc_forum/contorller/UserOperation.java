package com.bootcamp.bc_forum.contorller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.model.dto.UserDto;

public interface UserOperation {
  @GetMapping(value = "/users")
  List<UserDTO> getUsers();
}
