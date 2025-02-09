package com.bootcamp.demo.demo_sb_restful.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.demo_sb_restful.Model.Operation;

@Service
public class CalculatorService {
  public Integer operate (Operation operation ,
  Integer x, Integer y){
    return switch(operation){
      case SUM -> sum(x + y);
      case SUBTRACT ->subtract(x - y);
      case MULTIPLY -> multiply(x * y);
      case DIVIDE -> divide(x /y);
    };
    System.out.println("result=" + result);
  }



  public Integer sum(Integer x , Integer y ){
    return x + y;
  }
  public Integer subtract(Integer x ,Integer y){
    return x- y;
  }
  public Integer multiply(Integer x , Integer y){
    return x * y;
  }
  public Integer divide(Integer x , Integer y){
    return x /y;
  }
//! what if y =0??? and we don't use throw exception 
//! Learn @ControllerAdvice --> handle exception

  
}
