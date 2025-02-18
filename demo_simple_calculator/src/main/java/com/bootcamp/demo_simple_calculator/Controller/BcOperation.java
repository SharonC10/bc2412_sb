package com.bootcamp.demo_simple_calculator.Controller;

import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo_simple_calculator.Model.BcCalculatorAns;
import com.bootcamp.demo_simple_calculator.Model.BcCalculatorQuestion;
import com.bootcamp.demo_simple_calculator.Model.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public interface BcOperation {
  @GetMapping(value = "/operation")
  public BcCalculatorAns getOperation(@RequestParam String x,
   @RequestParam String y,@RequestParam Operation operation);
  
  @PostMapping(value = "/operation") 
  public BcCalculatorAns postOperation(@RequestBody BcCalculatorQuestion bcCalculatorQuestion);
  
  @GetMapping ("/operation/{x}/{y}/{operation}")
  public BcCalculatorAns getOperation2(@PathVariable String x,
  @PathVariable String y , @PathVariable Operation operation);
}
