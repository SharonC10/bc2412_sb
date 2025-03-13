package com.demospcotroller.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.demospcotroller.dto.CalculatorResult;

//Autowired (required = false )
//CalculateOperation calculatorOperation
// then method will call calculatorOperstion.xxx()

public interface CalculatorOperation {
  @GetMapping(value = "/calculate/{x}/{y}")
  public CalculatorResult calculate(
    @PathVariable int x, @PathVariable int y);
}
