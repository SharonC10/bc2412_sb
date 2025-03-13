package com.bootcamp.demo_yahoofinance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DataDto {
  private String symbol;
  private Long regularMarketTime;
  private Long regularMarketPrice;
  private Long bid;
  private Long ask;
}
