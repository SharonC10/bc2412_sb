package com.bootcamp.demo.demo_sb_restful.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_restful.Model.Operation;
import com.bootcamp.demo.demo_sb_restful.Service.CalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



//! "sum" -> 1+2 = 3
 //! subtract -> 1- 2 -> -1
 //! multiply -> 1 * 2 = 2
// !divide -> 9/3 -> 3
@RestController //! @Controller +  @ResponseBody
// @Controller -> 
//during server start -> create an object of CalculatorController 
//-> put is into context 
@RequestMapping(value = "/v1") //make URL longer
// What is @RequestMapping

public class CalculatorController {
 
  //-------------------------------------
  // http://localhost:8082/operation/sum?x=1&y=2
  // @GetMapping(value = "/operation/{operation}")
  // public Integer operate(@PathVariable Operation operation,
  // @RequestParam Integer x , @RequestParam Integer y ) {
  //     return switch(operation){
  //       case SUM -> x + y;
  //       case SUBTRACT ->x - y;
  //       case MULTIPLY ->x * y;
  //       case DIVIDE ->{
  //         int result = y == 0 ? 0: x/y;
          // yield result;
          // int result;
          // try {
          //   return x / y;
          // } catch (ArithmeticException e) {
          //  result = 0;
          // }
          // yield result;
  //       }
  //     };
      
  // }
  //-------------------------------------
  //! After Lunch New Topic - Service
  //@Autowired
  @Autowired
  private CalculatorService calculatorService; 

  //https://localhost:8082/v1/operation/SUM?x=1&y=2
  @GetMapping(value = "/operation/{operation}")
  public Integer operate(@PathVariable Operation operation,
  @RequestParam Integer x , @RequestParam Integer y) {
    return this.calculatorService.operate(operation, x, y);
 }
  
  //in spring boot Vincent usually use wrapper class

  @GetMapping(value = "/subtract")
  public Integer subtract(@RequestParam Integer subAns) {
      return 1-2;
  }
  
  @GetMapping(value = "/multiply")
  public Integer multiply(@RequestParam Integer multiplyAns) {
      return 1 * 2;
  }

  @GetMapping(value = "/divide")
  public Integer divide(@RequestParam Integer divideAns) {
      return 9 / 3;
  }
  
  


}
