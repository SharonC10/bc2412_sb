package com.bootcamp.demo_restiful.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cat {
 // wrapper class for serialization / deserialization
  private Long id; //long 0 (wrapper -> can be 'null')
  private String name;
  private Integer age ;

}
