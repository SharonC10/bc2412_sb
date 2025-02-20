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
import com.bootcamp.bc_forum.model.dto.PostDto;
import com.bootcamp.bc_forum.service.CommentService;
import com.bootcamp.bc_forum.service.PostService;

@Service
public class PostServiceImpl implements PostService{
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private CommentService commentService;
  
  @Value ("${api.jsonplaceholder.domain}")
  private String domain;

  @Value ("${api.jsonplaceholder.endpoints.posts}")
  private String postsEndpoint;

  @Override
  public List<UserDTO.Post> getPostDtos(Long userId){
    String url = UriComponentsBuilder.newInstance()
    .scheme("https")
    .host(domain)
    .path(postsEndpoint)
    .build()
    .toUriString();
    List<PostDto> postDtos = 
    Arrays.asList(this.restTemplate.getForObject(url, PostDto[].class));

   List<UserDTO.Post> postList = new ArrayList<>();

    for(PostDto postDto : postDtos){
      if(postDto.getUserId() == userId){
      postList.add(
        new UserDTO.Post(
        postDto.getId(),
        postDto.getTitle(),
        postDto.getBody(),
        commentService.getCommentDtos(postDto.getId())
        )
      );
    }
   } 
    return postList;
 
  }
}
