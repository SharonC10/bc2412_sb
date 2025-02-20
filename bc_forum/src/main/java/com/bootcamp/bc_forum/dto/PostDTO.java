package com.bootcamp.bc_forum.dto;

import java.util.List;
import com.bootcamp.bc_forum.dto.UserDTO.Post;
import com.bootcamp.bc_forum.model.dto.CommentDto;
import com.bootcamp.bc_forum.model.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostDTO {
  private Long id;
  private String username;
  private List<UserDTO.Post> posts;

  @Getter
  @AllArgsConstructor
  public static class Post {
    private Long id;
    private String title ;
    private String body;
    
  }
 
}

