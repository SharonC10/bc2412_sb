package com.bootcamp.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.demo.demo_sb_restful.Model.ErrorResult;

@ControllerAdvice //全局捕捉
public class GlobalExcerptionHandler { // 
  
  @ExceptionHandler(
    value = {ArithmeticException.class, BusinessException.class})
  @ResponseStatus (value = HttpStatus.BAD_REQUEST)
    public ErrorResult handleArithmentic(RuntimeException e){
    return new ErrorResult(e.getMessage());
  } 

  @ExceptionHandler(value = BusinessException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResult handleArithmentic(VincentException e){
    return new ErrorResult(e.getMessage());
  } 

}
