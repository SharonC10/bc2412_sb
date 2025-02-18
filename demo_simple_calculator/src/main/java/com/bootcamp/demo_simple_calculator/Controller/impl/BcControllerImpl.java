package com.bootcamp.demo_simple_calculator.Controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo_simple_calculator.Controller.BcOperation;
import com.bootcamp.demo_simple_calculator.Model.BcCalculatorAns;
import com.bootcamp.demo_simple_calculator.Model.BcCalculatorQuestion;
import com.bootcamp.demo_simple_calculator.Model.Operation;
import com.bootcamp.demo_simple_calculator.Service.BcCalculatorService;

@RestController
public class BcControllerImpl implements BcOperation {
@Autowired
private BcCalculatorService bcCalculatorService;

@Override
public BcCalculatorAns getOperation(String x, String y, Operation operation) {
      BcCalculatorQuestion bcCalculator = new BcCalculatorQuestion(x,y,operation); 

      // Double number1 = Double.valueOf(x); //String -> Double x
      // Double number2 = Double.valueOf(y); //String -> Double y

      return this.bcCalculatorService.result(bcCalculator);
      // "x" : "3.0" --> @request
      // "y" : "7.0" --> @request
      //"operation" : "mul" --> @request
      //"result" : "21.0" ---> result()
  }

  @Override
  public BcCalculatorAns postOperation(BcCalculatorQuestion bcCalculatorQuestion){
    return this.bcCalculatorService.result(bcCalculatorQuestion);
   }

   @Override
   public BcCalculatorAns getOperation2( String x,
   String y , Operation operation){
    BcCalculatorQuestion postBcCalculator = new BcCalculatorQuestion(x, y, operation);
    return this.bcCalculatorService.result(postBcCalculator);
   }
}

