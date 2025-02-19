package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.model.dto.UserDto;

public interface UserService {
  List<UserDTO> getUsers();
}
