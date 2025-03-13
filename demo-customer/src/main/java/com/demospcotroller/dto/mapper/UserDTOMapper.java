package com.demospcotroller.dto.mapper;

import org.springframework.stereotype.Component;
import com.demospcotroller.dto.UserDTO;
import com.demospcotroller.model.dto.UserDto;

@Component //bean
public class UserDTOMapper {
  //instance method  (can be no attribute)
 public UserDTO map(UserDto dto){
//create Object
  UserDTO.Address.Geo userAddressGeo = UserDTO.Address.Geo.builder()
  .latitude(dto.getAddress().getGeo().getLatitude())
  .longitude(dto.getAddress().getGeo().getLongitude())
  .build();

  //create Object 
  UserDTO.Address userAddress = UserDTO.Address.builder()
  .city(dto.getAddress().getCity())
  .street(dto.getAddress().getStreet())
  .suite(dto.getAddress().getSuite())
  .geo(userAddressGeo)
  .zipcode(dto.getAddress().getZipcode())
  .build();

  return UserDTO.builder().id(dto.getId())
  .name(dto.getName())
  .email(dto.getEmail())
  .username(dto.getUsername())
  .address(userAddress)
  .build();
  
 } 
}
