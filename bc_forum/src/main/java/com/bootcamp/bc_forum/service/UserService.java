package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.dto.CommentDTO;
import com.bootcamp.bc_forum.dto.PostDTO;
import com.bootcamp.bc_forum.dto.UserDTO;
import com.bootcamp.bc_forum.model.dto.NewCommentDto;
import com.bootcamp.bc_forum.model.dto.UserDto;

public interface UserService {
  List<UserDTO> getUsers();
  //List<CommentDTO> getCommentById(Long userId);
  List<CommentDTO> getCommentById(Long userId);
}
