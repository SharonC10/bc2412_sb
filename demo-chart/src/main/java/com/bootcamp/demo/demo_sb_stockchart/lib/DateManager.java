package com.bootcamp.demo.demo_sb_stockchart.lib;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateManager {
  private ZoneId zoneId;

  public static DateManager of(Zone zone) {
    return new DateManager(zone);
  }

  private DateManager(Zone zone) {
    this.zoneId = switch (zone) {
      case HK -> ZoneId.of("Asia/Hong_Kong");
    };
  }

  public long convert(LocalDate date) {
    return date.atStartOfDay(this.zoneId).toEpochSecond();
  }

  public long convert(LocalDateTime dateTime) {
    return dateTime.atZone(this.zoneId).toEpochSecond();
  }

  public static enum Zone {
    HK,;
  }
}
