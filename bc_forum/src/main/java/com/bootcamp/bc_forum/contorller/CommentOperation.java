package com.bootcamp.bc_forum.contorller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.bc_forum.model.dto.CommentDto;

public interface CommentOperation {
  @GetMapping (value = "/comments")
  List<CommentDto> getComments(@RequestParam Long postId);
}
