package com.bootcamp.demo_simple_calculator.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class BcCalculatorQuestion {
  private String x;
  private String y;
  private Operation operation;
}
