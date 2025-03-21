package com.bootcamp.demo.demo_sb_stockchart.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.demo_sb_stockchart.dto.LinePointDTO;

public interface LineChartOperation {
  /**
   * 
   * @param type i.e. type=FIVE_MINUTE
   * @return List<PricePoint>
   */
  @GetMapping(value = "/chart/line")
  List<LinePointDTO> getLineChart(@RequestParam String interval);
}
