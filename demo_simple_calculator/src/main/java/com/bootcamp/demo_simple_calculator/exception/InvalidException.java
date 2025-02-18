package com.bootcamp.demo_simple_calculator.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.bootcamp.demo_simple_calculator.codewave.BusinessException;
import com.bootcamp.demo_simple_calculator.codewave.GlobalExceptionHandler;

public class InvalidException extends GlobalExceptionHandler{
  // public InvalidException (String message){
  //   super(message);
  // }
  
}
