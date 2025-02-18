package com.bootcamp.demo_simple_calculator.codewave;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.bootcamp.demo_simple_calculator.exception.ErrorResult;
import com.bootcamp.demo_simple_calculator.exception.InvalidException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  // @ExceptionHandler (
  //   value = {NumberFormatException.class})
  // @ResponseStatus(HttpStatus.BAD_REQUEST)//400
  // public ErrorResult handleException (NumberFormatException e){
  //   return new ErrorResult(9, "Invalid Input");
  // }

  // @ExceptionHandler (
  //   value = {HttpMessageNotReadableException.class})
  // @ResponseStatus(HttpStatus.BAD_REQUEST)//400
  // public ErrorResult handleException (HttpMessageNotReadableException e){
  //   return new ErrorResult(9, "Invalid Input");
  // }
  @ExceptionHandler(
    value = {NumberFormatException.class, HttpMessageNotReadableException.class
    ,BusinessException.class})
  @ResponseStatus (value = HttpStatus.BAD_REQUEST)
    public ErrorResult handleArithmentic(RuntimeException e){
    return new ErrorResult(9, "Invalid Input");
  } 
  
}
