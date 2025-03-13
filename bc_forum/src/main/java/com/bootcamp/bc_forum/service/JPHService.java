package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.model.dto.CommentDto;
import com.bootcamp.bc_forum.model.dto.PostDto;
import com.bootcamp.bc_forum.model.dto.UserDto;

public interface JPHService {
  List<UserDto> getJPHUsers();
  List<PostDto> getJPHPosts();
  List<CommentDto> getJPHComments();
}