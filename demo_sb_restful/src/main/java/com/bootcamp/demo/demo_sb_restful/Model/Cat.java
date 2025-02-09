package com.bootcamp.demo.demo_sb_restful.Model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter

public class Cat {
  //Wrapper Class for serialization / deserialization
  private Long id;
  private String name;
  private Integer age;


}
