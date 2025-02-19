package com.bootcamp.bc_forum.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.model.dto.UserDto;
import com.bootcamp.bc_forum.service.PostService;
import com.bootcamp.bc_forum.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private PostService postService;

  @Value("${api.jsonplaceholder.domain}")
  private String domain;

  @Value("${api.jsonplaceholder.endpoints.users}")
  private String usersEndpoint;

  @Override
  public List<UserDTO> getUsers() {
    String url = UriComponentsBuilder.newInstance().scheme("https").host(domain)
        .path(usersEndpoint).build().toUriString();

    System.out.println("url" + url);
    List<UserDto> userDtos =
        Arrays.asList(this.restTemplate.getForObject(url, UserDto[].class));

    List<UserDTO> userList = new ArrayList<>();

    for (UserDto userDto : userDtos) {
      UserDTO userDTO = new UserDTO(//
        userDto.getId(), //
        userDto.getName(),//
        userDto.getUsername(), //
        userDto.getEmail(),//
          new UserDTO.Address(//
            userDto.getAddress().getStreet(),//
              userDto.getAddress().getSuite(), //
              userDto.getAddress().getCity(),//
              userDto.getAddress().getZipcode(),//
                new UserDTO.Address.Geo(//
                  userDto.getAddress().getGeo().getLat(),//
                  userDto.getAddress().getGeo().getLng()//
                  )//
                ),//
          userDto.getPhone(),//
          userDto.getWebsite(),//
            new UserDTO.Company(//
              userDto.getCompany().getName(),//
              userDto.getCompany().getCatchPhrase(),//
              userDto.getCompany().getBs()),//
          postService.getPostDtos(userDto.getId()));//

          userList.add(userDTO);

    }


    return userList;

  }


}
