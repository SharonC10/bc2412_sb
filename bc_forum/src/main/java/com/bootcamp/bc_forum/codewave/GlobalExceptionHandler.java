package com.bootcamp.bc_forum.codewave;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
  @ExceptionHandler(BusinessException.class)
  public ApiResp<Void> handleBusinessException (BusinessException e){
    return ApiResp.<Void>builder()
    .syscode(e.getSysCode())
    .build();
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ApiResp<Void> handleUserNotFoundException (UserNotFoundException e){
    return ApiResp.<Void>builder()
    .syscode(SysCode.One) //show Syscode One -> (1,user not found.)
    .build();
  }
  
}
