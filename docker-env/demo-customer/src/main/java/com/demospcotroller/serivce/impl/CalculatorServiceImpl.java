package com.demospcotroller.serivce.impl;

import org.springframework.stereotype.Service;
import com.demospcotroller.serivce.CalculatorService;
@Service
public class CalculatorServiceImpl implements CalculatorService{
  @Override 
  public int sum (int x , int y){
    return x + y;
  };

  public int subtract(int x ,int y){
    return x -y;
  };
}
