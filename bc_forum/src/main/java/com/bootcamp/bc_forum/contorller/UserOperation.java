package com.bootcamp.bc_forum.contorller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.bc_forum.dto.CommentDTO;
import com.bootcamp.bc_forum.dto.PostDTO;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.dto.CommentDTO.Comment;
import com.bootcamp.bc_forum.dto.PostDTO.Post;
import com.bootcamp.bc_forum.model.dto.UserDto;

public interface UserOperation {
  @GetMapping(value = "/users")
  List<UserDTO> getUsers();

  @GetMapping (value = "/getcommentbyid")
  List<CommentDTO> getCommentById(@RequestParam Long userId);

}
