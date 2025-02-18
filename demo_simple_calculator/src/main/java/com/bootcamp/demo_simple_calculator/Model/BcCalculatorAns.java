package com.bootcamp.demo_simple_calculator.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter

public class BcCalculatorAns {
  private Double x;
  private Double y;
  private Operation operation;
  private Double result;
}
