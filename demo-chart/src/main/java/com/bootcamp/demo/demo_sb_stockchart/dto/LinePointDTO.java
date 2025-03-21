package com.bootcamp.demo.demo_sb_stockchart.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LinePointDTO {
  private Long dateTime;
  private Double close;
}
