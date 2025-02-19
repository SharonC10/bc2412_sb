package com.bootcamp.bc_forum.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.model.dto.CommentDto;
import com.bootcamp.bc_forum.model.dto.PostDto;
import com.bootcamp.bc_forum.service.CommentService;
import com.bootcamp.bc_forum.service.PostService;
import com.bootcamp.bc_forum.service.mapper.UserMapperService;

// @Service
// public class UserMapperImpl implements UserMapperService{
//   @Autowired
//   private RestTemplate restTemplate;
//   @Autowired
//   private PostService postService;
//   @Autowired
//   private CommentService commentService;

//   @Value ("${api.jsonplaceholder.domain}")
//   private String domain;

//   @Value ("${api.jsonplaceholder.endpoints.allusers}")
//   private String postsEndpoint;

  // @Override
  // public List<UserDTO> getAllUsers(){
  //   String url = UriComponentsBuilder.newInstance()
  //   .scheme("https")
  //   .host(domain)
  //   .path(postsEndpoint)
  //   .build()
  //   .toUriString();

  //   // List<UserDTO> allUserDTOs = 
  //   // Post: user_id ---> User: id 
  //   List<PostDto> posts = postService.getPostDtos();
  //   // Comment: post_id ---> Post: id

  //   Arrays.asList(this.restTemplate.getForObject(url, UserDTO[].class));
  //   return allUserDTOs; 
//   // }
// }
