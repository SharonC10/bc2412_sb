package com.bootcamp.demo.demo_sb_stockchart.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.demo_sb_stockchart.dto.CandleStickDTO;

public interface CandleChartOperation {

  /**
   * 
   * @param type i.e. type=DAY
   * @return List<CandleStick>
   */
  @GetMapping(value = "/chart/candle")
  List<CandleStickDTO> getCandleChart(@RequestParam String interval);

}
