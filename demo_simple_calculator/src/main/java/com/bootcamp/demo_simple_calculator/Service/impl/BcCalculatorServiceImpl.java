package com.bootcamp.demo_simple_calculator.Service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

import com.bootcamp.demo_simple_calculator.Model.BcCalculatorAns;
import com.bootcamp.demo_simple_calculator.Model.BcCalculatorQuestion;
import com.bootcamp.demo_simple_calculator.Service.BcCalculatorService;

@Service
public class BcCalculatorServiceImpl implements BcCalculatorService{
   @Override
  public BcCalculatorAns result(BcCalculatorQuestion bcCalculatorQuestion) {
      BcCalculatorAns result = switch (bcCalculatorQuestion.getOperation()) {
        case SUM -> sum(bcCalculatorQuestion);
        case SUBTRACT -> subtract(bcCalculatorQuestion);
        case MULTIPLY -> multiply(bcCalculatorQuestion);
        case DIVIDE -> divide(bcCalculatorQuestion);
      };
      
      return result;
    }
  
  
  
    public BcCalculatorAns sum(BcCalculatorQuestion bcCalculatorQuestion){
      //Ans (x,y,operation,result) ---> sun() ---> String  to double 
      Double numberX = Double.valueOf(bcCalculatorQuestion.getX());
      Double numberY = Double.valueOf(bcCalculatorQuestion.getY());
      return new BcCalculatorAns(numberX,numberY,bcCalculatorQuestion.getOperation()
      ,numberX + numberY);
    }
    public  BcCalculatorAns subtract(BcCalculatorQuestion bcCalculatorQuestion){
      Double numberX = Double.valueOf(bcCalculatorQuestion.getX());
      Double numberY = Double.valueOf(bcCalculatorQuestion.getY());
      return new BcCalculatorAns(numberX,numberY,bcCalculatorQuestion.getOperation()
      ,numberX - numberY);
    }
    public  BcCalculatorAns multiply(BcCalculatorQuestion bcCalculatorQuestion){
      Double numberX = Double.valueOf(bcCalculatorQuestion.getX());
      Double numberY = Double.valueOf(bcCalculatorQuestion.getY());
      return new BcCalculatorAns(numberX,numberY,bcCalculatorQuestion.getOperation()
      ,numberX * numberY);
    }
    public BcCalculatorAns divide(BcCalculatorQuestion bcCalculatorQuestion){
      Double numberX = Double.valueOf(bcCalculatorQuestion.getX());
      Double numberY = Double.valueOf(bcCalculatorQuestion.getY());
      // Double result = BigDecimal.valueOf(numberX)//
      // .divide(BigDecimal.valueOf(numberY))//
      // .setScale(5,RoundingMode.UP).doubleValue();
      Double result = BigDecimal.valueOf(numberX)
            .divide(BigDecimal.valueOf(numberY), 5, RoundingMode.HALF_UP)
            .doubleValue();

      return new BcCalculatorAns(numberX,numberY,bcCalculatorQuestion.getOperation()
      ,result);
    }
}
