package com.bootcamp.demo_simple_calculator.codewave;

import lombok.Getter;

@Getter

public enum SysCode {
  BAD_REQUEST("9","Invalid Input.")
  ;

  private final String message;
  private final String code;
  private SysCode(String code, String message){
    this.code = code;
    this.message = message;
  }
}
