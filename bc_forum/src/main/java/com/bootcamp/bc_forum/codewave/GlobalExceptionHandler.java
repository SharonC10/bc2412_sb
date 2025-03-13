package com.bootcamp.bc_forum.codewave;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.val;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(value = UserNotFoundException.class)
  @ResponseStatus (HttpStatus.BAD_REQUEST)
  public ErrorResult handleUserNotFoundException() {
    return ErrorResult.builder()//
    .code(1L)//
    .message("User not found.")//
    .build();
  }

  @ExceptionHandler (value = NumberFormatException.class)
  @ResponseStatus (HttpStatus.BAD_REQUEST)
  public ErrorResult handleNumberFormatExcption(){
    return ErrorResult.builder()//
    .code(2L)//
    .message("Invalid Input.")//
    .build();
  }

  @ExceptionHandler (value = RestClientException.class)
  @ResponseStatus (HttpStatus.BAD_REQUEST)
  public ErrorResult handleRestClientException(){
    return ErrorResult.builder()//
    .code(3L)//
    .message("RestTemplate Error - JsonPlaceHolder.")//
    .build();
  }
}
