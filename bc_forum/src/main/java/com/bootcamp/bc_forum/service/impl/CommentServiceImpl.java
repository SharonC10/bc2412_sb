// package com.bootcamp.bc_forum.service.impl;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;
// import org.springframework.web.util.UriComponentsBuilder;
// import com.bootcamp.bc_forum.dto.UserDTO;
// import com.bootcamp.bc_forum.model.dto.CommentDto;
// import com.bootcamp.bc_forum.model.dto.UserDto;
// import com.bootcamp.bc_forum.service.CommentService;
// @Service
// public class CommentServiceImpl implements CommentService{
//   @Autowired
//   private RestTemplate restTemplate;

//   @Value ("${api.jsonplaceholder.domain}")
//   private String domain;
//   @Value ("${api.jsonplaceholder.endpoints.comments}")
//   private String commentsEndpoints;

//   @Override
//   public List<CommentDto> getCommentDtos(Long postId){
//     String url = UriComponentsBuilder.newInstance()
//     .scheme("https")
//     .host(domain)
//     .path(commentsEndpoints)
//     .build()
//     .toUriString();
//     List<CommentDto> commentDtos = 
//     Arrays.asList(this.restTemplate.getForObject(url, CommentDto[].class));

//     List<CommentDto> filteredCommentDtos = commentDtos
//     .stream()
//     .filter(e -> e.getPostId() == postId)
//     .toList();

    
//     return filteredCommentDtos;
//   }
 
// }
