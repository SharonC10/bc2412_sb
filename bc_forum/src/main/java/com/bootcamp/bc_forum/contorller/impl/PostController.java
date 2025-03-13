// package com.bootcamp.bc_forum.contorller.impl;

// import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.RestController;
// import com.bootcamp.bc_forum.contorller.PostOperation;
// import com.bootcamp.bc_forum.dto.UserDTO;
// import com.bootcamp.bc_forum.model.dto.PostDto;
// import com.bootcamp.bc_forum.service.PostService;
// @RestController
// public class PostController implements PostOperation {
//   @Autowired
//   private PostService postService;
//   @Override
//   public List<UserDTO.Post> getPosts(Long userId){
//     return this.postService.getPostDtos(userId);
//   }
// }
