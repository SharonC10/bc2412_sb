package com.demospcotroller.serivce;

import java.util.List;
import com.demospcotroller.model.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface UserService {
  List<UserDto> getUsers()throws JsonProcessingException;
}
