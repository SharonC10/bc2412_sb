package com.bootcamp.demo_yahoofinance.config;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bootcamp.demo_yahoofinance.dto.DataDTO;
import com.bootcamp.demo_yahoofinance.entity.TStockPriceEntity;
import com.bootcamp.demo_yahoofinance.entity.TStocksEntity;
import com.bootcamp.demo_yahoofinance.lib.RedisManager;
import com.bootcamp.demo_yahoofinance.lib.YahooFinanceManager;
import com.bootcamp.demo_yahoofinance.model.dto.QuoteResponseDto;
import com.bootcamp.demo_yahoofinance.repository.TStocksPriceRepo;
import com.bootcamp.demo_yahoofinance.service.imp.YHFinanceService;

@Component
public class ScheduleConfig {

  private final RedisManager redisManager;
  private YahooFinanceManager yahooFinanceManager;
  private YHFinanceService yhFinanceService;
  @Autowired
  private TStocksPriceRepo tStocksPriceRepo;


    ScheduleConfig(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

  @Scheduled(fixedDelay = 30000) // 每 5 分鐘執行--------------------------------
  public void quoteData() throws Exception {

    // 0800 ---> yesterday record-------------------------------
    // ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Hong Kong"));
    // LocalTime currentTime = now.toLocalTime();
    // if (currentTime.isBefore(LocalTime.of(8, 0))){
    //   TStockPriceEntity lastData = yhFinanceService.findLastDataForPreviousDay();
    //   if (lastData != null){
    //     // ! what is last Data???? 
    //     redisManager.set("PRICE-LIST", lastData.getSymbol(), Duration.ofHours(24));
    //   }
    // }
    // //0800 - 0930 redis clear data-------------------------------
    // else if (currentTime.isAfter(LocalTime.of(8, 0))&& 
    // currentTime.isBefore(LocalTime.of(9, 30))) {
    //   redisManager.clearAll();
    // }

    // // 0935 save data ---> DB and redis-------------------------------
    // else if (currentTime.isAfter(LocalTime.of(9, 35))){
    //   List<TStockPriceEntity> stockPriceList = yhFinanceService.getPriceCache();//get from redis (if null -> get from DB)

    // quote data(388 price ) ---> base on redis no.-------------------------------
    List<TStocksEntity> stockList = yhFinanceService.getStockListWithCache();
    List<DataDTO> dataList = new ArrayList<>();
    
    for (TStocksEntity stock : stockList) {
      String symbol = stock.getSymbol();
      QuoteResponseDto quoteResponse = yahooFinanceManager // call api
          .quote(symbol);

      // Dto --> DTO------------------------------------------------------------
      DataDTO dataDTO = new DataDTO();
      dataDTO.setSymbol(symbol);
      dataDTO.setRegularMarketTime(quoteResponse.getQuoteResponse().getResult()
          .get(0).getRegularMarketTime());
      dataDTO.setRegularMarketPrice(quoteResponse.getQuoteResponse().getResult()
          .get(0).getRegularMarketPrice());
      dataDTO.setRegularMarketChangePercent(quoteResponse.getQuoteResponse()
          .getResult().get(0).getRegularMarketChangePercent());
      dataDTO
          .setBid(quoteResponse.getQuoteResponse().getResult().get(0).getBid());
      dataDTO
          .setAsk(quoteResponse.getQuoteResponse().getResult().get(0).getAsk());

      dataList.add(dataDTO);

      // time stamp (json)----> Unix time ---------------------
      long regularMarketUnixTime = dataDTO.getRegularMarketTime(); // get json timeStamp
      ZonedDateTime regularMarketDatTime =
          Instant.ofEpochMilli(regularMarketUnixTime)
              .atZone(ZoneId.of("Asia/Hong Kong"));


      // Type ---> 5min------------------------------------------------------------
      String type = "5 mins";


      // Currnet Time Stamp------------------------------------------------------------
      Instant currentTimeStamp = Instant.now();
      long currentTimeMillis = currentTimeStamp.toEpochMilli();
      ZonedDateTime currZonedDateTime =
          ZonedDateTime.now(ZoneId.of("Asia/Hong Kong"));



      // set DTO ----> Entity & save------------------------------------------------------------
      TStockPriceEntity priceEntity = TStockPriceEntity.builder()
          .symbol(dataDTO.getSymbol()).ask(dataDTO.getAsk())
          .bid(dataDTO.getBid()).regularMarketTime(regularMarketDatTime)
          .regularMarketChangePercent(dataDTO.getRegularMarketChangePercent())
          .regularMarketPrice(dataDTO.getRegularMarketPrice()).type(type)
          .apiTimeStamp(currZonedDateTime).build();

      tStocksPriceRepo.save(priceEntity);
    }
      // ----save in redis------------------------------------------------------------
      List<TStockPriceEntity> priceLists = yhFinanceService.getPriceCache();
    }


   
  }


