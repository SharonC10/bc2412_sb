package com.bootcamp.bc_forum.codewave;

public class RestTemplateException extends RuntimeException {
  private SysCode sysCode;

  public static RestTemplateException of(SysCode sysCode){
    return new RestTemplateException(sysCode);
  }

  private RestTemplateException (SysCode sysCode){
    super(sysCode.getMessage());
    this.sysCode = sysCode;
  }
  public SysCode getSysCode(){
    return this.sysCode;
  }
}
