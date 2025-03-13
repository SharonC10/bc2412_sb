package com.bootcamp.bc_forum.codewave;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResult {
  private Long code;
  private String message;
}
