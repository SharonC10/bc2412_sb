package com.bootcamp.demo_simple_calculator.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

public class ErrorResult {
  private final String message;
  private final int code;

public ErrorResult (int code , String message){
  this.code = 9;
  this.message = message;
}
}
