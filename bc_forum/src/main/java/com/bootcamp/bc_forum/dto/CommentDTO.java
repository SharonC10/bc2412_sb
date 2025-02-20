package com.bootcamp.bc_forum.dto;

import java.util.List;
import com.bootcamp.bc_forum.dto.UserDTO.Post.Comment;
import com.bootcamp.bc_forum.model.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDTO {
  private Long id;
  private String username;
  private List<Comment> comment;

  @Getter
  @AllArgsConstructor
  public static class Comment{
    private String name ;
    private String email;
    private String body;
  }
}
