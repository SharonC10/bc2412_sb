package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.model.dto.CommentDto;

public interface CommentService {
  List<CommentDto> getCommentDtos(Long postId);
}
