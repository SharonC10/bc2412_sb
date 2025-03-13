package com.demospcotroller.entity.mapper;

import org.springframework.stereotype.Component;
import com.demospcotroller.entity.AddressEntity;
import com.demospcotroller.entity.CompanyEntity;
import com.demospcotroller.entity.GeoEntity;
import com.demospcotroller.entity.UserEntity;
import com.demospcotroller.model.dto.UserDto;

@Component //What's that mean?? 
// smthing already exist
public class EntityMapper {
  
  public UserEntity map(UserDto dto){
    return UserEntity.builder()
    .email(dto.getEmail())
    .name(dto.getName())
    .username(dto.getUsername())
    .phone(dto.getPhone())
    .build();
  }

  public AddressEntity map(UserDto.Address address){
    return AddressEntity.builder()
    .street(address.getStreet())
    .city(address.getCity())
    .suite(address.getSuite())
    .zipcode(address.getZipcode())
    .build();
  }

  public GeoEntity map(UserDto.Address.Geo geo){
    return GeoEntity.builder()
    .latitude(Double.valueOf(geo.getLatitude()))
    .longitude(Double.valueOf(geo.getLongitude()))
    .build();
  }

  public CompanyEntity map(UserDto.Company company){
    return CompanyEntity.builder()
    .name(company.getName())
    .catchPhrase(company.getCatchPhrase())
    .bs(company.getBs())
    .build();
  }
}
