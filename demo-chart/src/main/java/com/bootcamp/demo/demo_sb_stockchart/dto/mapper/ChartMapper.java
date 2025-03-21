package com.bootcamp.demo.demo_sb_stockchart.dto.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo.demo_sb_stockchart.dto.CandleStickDTO;
import com.bootcamp.demo.demo_sb_stockchart.dto.LinePointDTO;
import com.bootcamp.demo.demo_sb_stockchart.lib.DateManager;
import com.bootcamp.demo.demo_sb_stockchart.lib.DateManager.Zone;
import com.bootcamp.demo.demo_sb_stockchart.model.CandleStick;
import com.bootcamp.demo.demo_sb_stockchart.model.LinePoint;

@Component
public class ChartMapper {
  public CandleStickDTO map(CandleStick candle) {
    long unixtimeDate = DateManager.of(Zone.HK).convert(candle.getDate());
    return CandleStickDTO.builder() //
        .date(unixtimeDate) //
        .open(candle.getOpen()) //
        .close(candle.getClose()) //
        .high(candle.getHigh()) //
        .low(candle.getLow()) //
        .build();
  }

  public LinePointDTO map(LinePoint point) {
    long unixtimeDatetime =
        DateManager.of(Zone.HK).convert(point.getDateTime());
    return LinePointDTO.builder() //
        .dateTime(unixtimeDatetime) //
        .close(point.getClose()) //
        .build();
  }
}
