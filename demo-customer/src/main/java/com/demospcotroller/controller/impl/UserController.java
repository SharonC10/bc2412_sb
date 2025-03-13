package com.demospcotroller.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.demospcotroller.controller.UserOperation;
import com.demospcotroller.dto.UserDTO;
import com.demospcotroller.dto.mapper.UserDTOMapper;
import com.demospcotroller.serivce.UserService;


@RestController
public class UserController implements UserOperation {
  @Autowired
  private UserService userService;
  @Autowired
  private UserDTOMapper userDTOMapper;

  // @Override
  // public List<UserDTO> getUser() {
  //   // List of UserDto -> List of UserDTO
  //   return this.userService.getUser().stream()
  //   .map(e -> 
  //   UserDTO.builder()
  //       .id(e.getId())
  //       .name(e.getName())
  //       .username(e.getUsername())
  //       .email(e.getEmail())
  //       .address(
  //         UserDTO.Address.builder()
  //         .street(e.getAddress().getStreet())
  //           .suite(e.getAddress().getSuite())
  //           .city(e.getAddress().getCity())
  //           .zipcode(e.getAddress().getZipcode())
  //           .geo(
  //             UserDTO.Address.Geo.builder()
  //               .latitude(e.getAddress().getGeo().getLatitude())
  //               .longitude(e.getAddress().getGeo().getLongitude())
  //               .build())
  //               .build())
  //                   .build()
  //       )
  //       .collect(Collectors.toList());
  // }

  @Override
  public List<UserDTO> getUsers(){
    return this.userService.getUsers().stream()
    .map(e -> userDTOMapper.map(e))
    .collect(Collectors.toList());

  // List<UserDto> serviceResult = this.userService.getUser();
  // List<UserDTO> userDTOs =new ArrayList<>();
  //   for (UserDto dto : serviceResult){
  //     UserDTO userDTO = userDTOMapper.map(dto);
  //     userDTO.add(userDTO);
  //     } 
  //     return userDTOs;
}
  

}
