package com.bootcamp.demo_simple_calculator.codewave;

public class NumberFormatException extends RuntimeException{
  public NumberFormatException(String message ){
    super(message);
  }

  // public static NumberFormatException of(SysCode sysCode){
  //   return new NumberFormatException(sysCode);
  // }


  // private BusinessException(SysCode sysCode) {
  //   super(sysCode.getMessage());
  //   this.syscode = sysCode;
  // }

  // public SysCode getSysCode(){
  //   return this.syscode;
  // }

}
