package com.bootcamp.demo.demo_sb;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter //Serialization (object  -> JSON)
//@AllArgsConstructor

public class Cat {
  private String name;
  private int age;

  public Cat (String name , int age){ 
    this.name = name;
    this.age = age;
  }

}
