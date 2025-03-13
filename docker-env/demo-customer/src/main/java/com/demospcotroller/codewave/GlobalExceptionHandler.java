package com.demospcotroller.codewave;

import org.springframework.web.bind.annotation.ExceptionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;

public class GlobalExceptionHandler {
  @ExceptionHandler(BusinessException.class)
  public ApiResp<Void> handleBusinessException(BusinessException e) {
    return ApiResp.<Void>builder()//
        .syscode(e.getSysCode())//
        .build();
  }
  // if nothing return <Void>
  // data(null) ---> if u don't set show null.

  @ExceptionHandler(NullPointerException.class)
  public ApiResp<Void> handleNullPointerException(NullPointerException e) {
    return ApiResp.<Void>builder()//
        .syscode(SysCode.RTE_NPE)//
        .build();
  }

  @ExceptionHandler(JsonProcessingException.class)
  public ApiResp<Void> handleJsonProcessingException(JsonProcessingException e) {
    return ApiResp.<Void>builder()//
        .syscode(SysCode.RTE_NPE)//
        .build();
  }
}
