package com.bootcamp.bc_forum.codewave;

public enum SysCode {
  One(1,"User not found."),
  Two(2,"Invild input."),
  Three(3,"RestTemplate Error - JsonPlaceHolder")
  ;

  private final int code;
  private final String message;
  private SysCode (int code, String message){
    this.code = code;
    this.message =message;
  }

  public int getCode(){
    return this.code;
  }
  public String getMessage(){
    return this.message;
  }

}
