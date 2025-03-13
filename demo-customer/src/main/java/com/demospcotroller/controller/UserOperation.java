package com.demospcotroller.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.demospcotroller.dto.UserDTO;

public interface UserOperation {
  @GetMapping(value = "/users")
  List<UserDTO> getUsers();
}
