package com.bootcamp.demo_yahoofinance.controller;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo_yahoofinance.config.ScheduleConfig;
import com.bootcamp.demo_yahoofinance.entity.TStockPriceEntity;
import com.bootcamp.demo_yahoofinance.lib.RedisManager;
import com.bootcamp.demo_yahoofinance.service.imp.YHFinanceService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class YHFinanceController {
  @Autowired
  private ScheduleConfig scheduleConfig;
  @Autowired
  private YHFinanceService yhFinanceService;
  @Autowired
  private RedisManager redisManager;

  @GetMapping(value = "/price")
  public TStockPriceEntity getPrice(@RequestParam String symbol) throws IOException {
    return yhFinanceService.getLatestStockPrice(symbol);
}
  
//   @GetMapping (value = "/stockprice")
//   public List<TStockPriceEntity> getLatestPrice(@RequestParam String symbol) throws JsonProcessingException {
//     LocalTime currentTime = LocalTime.now(ZoneId.of("Asia/Hong_Kong"));
//     ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Hong_Kong"));
//     DayOfWeek dayOfWeek = now.getDayOfWeek();

//     if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){ 
//       // return last trade data 
//       ZonedDateTime lastTradingDate = 
//       now.minusDays(dayOfWeek == DayOfWeek.SATURDAY ? 1 : 2)
//       //! if the day is saturday -1 --> friday
//       //! if sunday -2 ---> friday
//       //! because base on the above condition is (Sunday / Saturday)
//       .withHour(16).withMinute(59).withSecond(59);
//       return //yhFinanceService.getPriceCache();
//       yhFinanceService.getLastDataFromCacheOrDB(symbol, lastTradingDate);
//     }

//     //! LunchTime 
//     if (currentTime.isAfter(LocalTime.of(12, 0)) && currentTime.isBefore(LocalTime.of(13, 0))){ 
//       // return before 1200 data
//       return yhFinanceService.getLastDataFromCacheOrDB(symbol, now.minusMinutes(1));//
//     } //! 之後Front End 需要隱藏此時後數據


//     // Redis --> 0800-0900 will clean yesterday data 
//     // database 
//     if (currentTime.isBefore(LocalTime.of(8, 0))) {
//         // 在 08:00 之前，獲取昨日最後的數據
//         ZonedDateTime yesterdayEnd = now.minusDays(1).withHour(16).withMinute(59).withSecond(0);
//         return yhFinanceService.getLastDataFromCacheOrDB(symbol, yesterdayEnd);
//     } else if (currentTime.isAfter(LocalTime.of(8, 0)) && currentTime.isBefore(LocalTime.of(9, 30))) {
//         // 在 08:00 到 09:30 之間，獲取今天的開市數據
//         ZonedDateTime startTime = now.withHour(9).withMinute(30).withSecond(0); // 開市時間
//         redisManager.clearAll();//! clean PRICE-LIST:*
//         return yhFinanceService.getOpeningData(symbol, startTime);//!
//     } else if (currentTime.isAfter(LocalTime.of(9, 30))) {
//         // 在 09:30 之後，獲取當前的即時數據
//         return yhFinanceService.getLiveData(symbol);//! 
//     }

//     return  yhFinanceService.getLiveData(symbol);
//     //List.of(); // 如果不匹配任何條件，返回空列表
// }













  //   // 0800 ---> yesterday record-------------------------------
  //  // ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Hong Kong"));
  //   //LocalTime currentTime = now.toLocalTime();
  //   if (currentTime.isBefore(LocalTime.of(8, 0))){
  //     //TStockPriceEntity lastData = yhFinanceService.findLastDataForPreviousDay();
  //     List<TStockPriceEntity> data = yhFinanceService.getPriceCache();
      
  //   }
  //   //0800 - 0930 redis clear data-------------------------------
  //   else if (currentTime.isAfter(LocalTime.of(8, 0))&& 
  //   currentTime.isBefore(LocalTime.of(9, 30))) {
  //     redisManager.clearAll();
  //   }

  //   // 0935 save data ---> DB and redis-------------------------------
  //   else if (currentTime.isAfter(LocalTime.of(9, 35))){
  //     List<TStockPriceEntity> stockPriceList = yhFinanceService.getPriceCache();//get from redis (if null -> get from DB)

  //   // quote data(388 price ) ---> base on redis no.-------------------------------
  // }

}