package com.bootcamp.demo_simple_calculator.Service;

import org.springframework.stereotype.Service;
import com.bootcamp.demo_simple_calculator.Model.BcCalculatorAns;
import com.bootcamp.demo_simple_calculator.Model.BcCalculatorQuestion;
import com.bootcamp.demo_simple_calculator.Model.Operation;

@Service
public interface BcCalculatorService {
  BcCalculatorAns result(BcCalculatorQuestion bcCalculatorQuestion);
}
