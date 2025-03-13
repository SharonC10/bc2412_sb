package com.demospcotroller.serivce.impl;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.demospcotroller.entity.AddressEntity;
import com.demospcotroller.entity.CompanyEntity;
import com.demospcotroller.entity.GeoEntity;
import com.demospcotroller.entity.UserEntity;
import com.demospcotroller.entity.mapper.EntityMapper;
import com.demospcotroller.model.dto.UserDto;
import com.demospcotroller.repository.AddressRepository;
import com.demospcotroller.repository.CompanyRepository;
import com.demospcotroller.repository.GeoRepository;
import com.demospcotroller.repository.UserRepository;
import com.demospcotroller.serivce.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private AddressRepository addressRepository;
  @Autowired
  private GeoRepository geoRepository;
  @Autowired
  private CompanyRepository companyRepository;
  @Autowired
  private EntityMapper entityMapper;

  
@Value ("${api.jsonplaceholder.domain}")
private String domain;

@Value ("${api.jsonplaceholder.endpoints.users}")
private String usersEndpoint;
@Autowired
  private RedisTemplate<String, String> redisTemplate;

  @Override
  public List<UserDto> getUsers()throws JsonProcessingException {
    // Cache Pattern: Read Through
    //! 1. Read Redis first, if found, return users
    String json = this.redisTemplate.opsForValue().get("jph-users");
    //! 【 where is this jph-user from? 】 
    // "[{},{},{}]" -> Java Object (Deserialization)
    ObjectMapper objectMapper = new ObjectMapper();
    // ! 【 What is this ObjectMapp? 】
    if (json != null) { //如果不是null 即有data can readValue
      UserDto[] userDtos = objectMapper.readValue(json, UserDto[].class);
      return Arrays.asList(userDtos);
    }

    //! 2. if not found, call JPH
    // String url = "https://jsonplaceholder.typicode.com/users";
    String url = UriComponentsBuilder.newInstance().scheme("https").host(domain)
        .path(usersEndpoint).build().toUriString();
    System.out.println("url=" + url);

    List<UserDto> userDtos =
        Arrays.asList(this.restTemplate.getForObject(url, UserDto[].class));
    // Clear DB
    this.geoRepository.deleteAll();
    this.addressRepository.deleteAll();
    this.companyRepository.deleteAll();
    this.userRepository.deleteAll();

    // Save DB (procedures)
    userDtos.stream().forEach(e -> {
      UserEntity userEntity =
          this.userRepository.save(this.entityMapper.map(e));

      AddressEntity addressEntity = this.entityMapper.map(e.getAddress());
      addressEntity.setUserEntity(userEntity);
      this.addressRepository.save(addressEntity);

      CompanyEntity companyEntity = this.entityMapper.map(e.getCompany());
      companyEntity.setUserEntity(userEntity);
      this.companyRepository.save(companyEntity);

      GeoEntity geoEntity = this.entityMapper.map(e.getAddress().getGeo());
      geoEntity.setAddressEntity(addressEntity);
      this.geoRepository.save(geoEntity);
    });
    //! 3. Write the users back to redis
    // Java Object -> "[{},{},{}]" (Serialization)
    String serializedJson = objectMapper.writeValueAsString(userDtos);
    this.redisTemplate.opsForValue().set("jph-users", serializedJson, Duration.ofMinutes(1));
    return userDtos;
  }
  
//   @Override
//   public List<UserDto> getUsers(){
//     //String url = "https://jsonplaceholder.typicode.com/users";
//     // Save user List to DB. & everytime Postman "send" -> clean the data first ,then insert into
//    String url = UriComponentsBuilder.newInstance()
//     .scheme("https")
//     .host(domain)
//     .path(usersEndpoint)
//     .build()
//     .toUriString(); //消化special characters

//     System.out.println("url" + url);

//     List<UserDto> userDtos =
//     Arrays.asList( this.restTemplate.getForObject(url, UserDto[].class));
//     // Clear DB 
//     this.geoRepository.deleteAll();
//     this.addressRepository.deleteAll();
//     this.companyRepository.deleteAll();
//     this.userRepository.deleteAll();
//     // userDtos.stream().forEach(e -> {
//     //   UserEntity userEntity = this.entityMapper.map(e);
//     //   this.userRepository.delete(this.entityMapper.map(e));

//     //   AddressEntity addressEntity = this.entityMapper.map(e.getAddress());
//     //   addressEntity.setUserEntity(userEntity);
//     //   this.addressRepository.delete(addressEntity);

//     //   GeoEntity geoEntity = this.entityMapper.map(e.getAddress().getGeo());
//     //   geoEntity.setAddressEntity(addressEntity);
//     //   this.geoRepository.delete(geoEntity);

//     //   CompanyEntity companyEntity = this.entityMapper.map(e.getCompany());
//     //   this.companyRepository.delete(companyEntity);
//     //     });
// //-------------------------------------------------------------------------------
//     //Save DB(procedures)
//     userDtos.stream().forEach(e-> { 
//       UserEntity userEntity = this.userRepository.save(this.entityMapper.map(e));

//       AddressEntity addressEntity = this.entityMapper.map(e.getAddress());
//       addressEntity.setUserEntity(userEntity);
//       this.addressRepository.save(addressEntity);

//       GeoEntity geoEntity = this.entityMapper.map(e.getAddress().getGeo());
//       geoEntity.setAddressEntity(addressEntity);
//       this.geoRepository.save(geoEntity);

//       CompanyEntity companyEntity = this.entityMapper.map(e.getCompany());
//       companyEntity.setUserEntity(userEntity);
//       this.companyRepository.save(companyEntity);
//       //e is the Object of List<UserDto> 
//       //e -> entity? user mapper -> create a "map()" method 
//     });
//     return userDtos;
//   }

}
