package com.bootcamp.bc_forum.service.mapper;

import java.util.List;
import com.bootcamp.bc_forum.dto.UserDTO;

public interface UserMapperService {
  List<UserDTO> getAllUsers();
}
