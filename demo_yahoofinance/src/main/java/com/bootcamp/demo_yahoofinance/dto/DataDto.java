package com.bootcamp.demo_yahoofinance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DataDTO {
  private String symbol;
  private Long regularMarketTime;
  private Double regularMarketPrice;
  private Double regularMarketChangePercent;
  private Double bid;
  private Double ask;
}
