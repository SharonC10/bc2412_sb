package com.demospcotroller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Order {
  private Long id;
  private Double amount;

}
