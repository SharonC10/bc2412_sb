// package com.bootcamp.bc_forum.contorller.impl;

// import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.RestController;
// import com.bootcamp.bc_forum.contorller.CommentOperation;
// import com.bootcamp.bc_forum.model.dto.CommentDto;
// import com.bootcamp.bc_forum.service.CommentService;
// @RestController
// public class CommentController implements CommentOperation {
//   @Autowired
//   private CommentService commentService;

//   @Override
//   public List<CommentDto> getComments(Long postId){
//     return this.commentService.getCommentDtos(postId);
//   }
// }
