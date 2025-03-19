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

  @Autowired
  private RedisManager redisManager;
  @Autowired
  private YahooFinanceManager yahooFinanceManager;
  @Autowired
  private YHFinanceService yhFinanceService;
  @Autowired
  private TStocksPriceRepo tStocksPriceRepo;

  @Scheduled(fixedDelay = 300000) // 每 5 分鐘執行--------------------------------
  public void quoteData() throws Exception {

    try{
    List<TStocksEntity> stockList = 
    yhFinanceService.getStockListWithCache();
    List<DataDTO> dataList = new ArrayList<>();
    
    for (TStocksEntity stock : stockList) {
      String symbol = stock.getSymbol();
      //!
      String formattedSymbol = symbol.replace(".HK", "");

      QuoteResponseDto quoteResponse = yahooFinanceManager // call api
          .quote(formattedSymbol);

          if (quoteResponse.getQuoteResponse().getResult().isEmpty()){ 
            System.out.println("No data found for symbol: "+ symbol);
            continue;
          }
        
      // Dto --> DTO------------------------------------------------------------
      DataDTO dataDTO = new DataDTO();
      dataDTO.setSymbol(quoteResponse.getQuoteResponse().getResult().get(0).getSymbol());
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

      System.out.println(dataDTO.getRegularMarketTime());

      // save in database 
 TStockPriceEntity priceEntity = TStockPriceEntity.builder()
 .symbol(dataDTO.getSymbol())
 .ask(dataDTO.getAsk())
 .bid(dataDTO.getBid())
 .regularMarketTime(Instant.ofEpochMilli(dataDTO.getRegularMarketTime()*1000).atZone(ZoneId.of("Asia/Hong_Kong")))
 .regularMarketChangePercent(dataDTO.getRegularMarketChangePercent())
 .regularMarketPrice(dataDTO.getRegularMarketPrice())
 .type("5 mins")
 .apiTimeStamp(ZonedDateTime.now(ZoneId.of("Asia/Hong_Kong")))
 .build();
 

 System.out.println(priceEntity.getRegularMarketTime());


  //save in Redis
 tStocksPriceRepo.save(priceEntity);
        }}catch (Exception e){ 
          System.out.println("Error occurred while fetching stock data " + e.getMessage());
          e.printStackTrace();
        }









        
      }
    }
  
      // time stamp (json)----> Unix time ---------------------
    //   long regularMarketUnixTime = dataDTO.getRegularMarketTime(); // get json timeStamp
    //   ZonedDateTime regularMarketDatTime =
    //       Instant.ofEpochMilli(regularMarketUnixTime)
    //           .atZone(ZoneId.of("Asia/Hong Kong"));


    //   // Type ---> 5min------------------------------------------------------------
    //   String type = "5 mins";


    //   // Currnet Time Stamp------------------------------------------------------------
    //   ZonedDateTime currZonedDateTime =
    //       ZonedDateTime.now(ZoneId.of("Asia/Hong Kong"));


    //   // set DTO ----> Entity & save------------------------------------------------------------
    //   TStockPriceEntity priceEntity = TStockPriceEntity.builder()
    //       .symbol(dataDTO.getSymbol())
    //       .ask(dataDTO.getAsk())
    //       .bid(dataDTO.getBid())
    //       .regularMarketTime(regularMarketDatTime)
    //       .regularMarketChangePercent(dataDTO.getRegularMarketChangePercent())
    //       .regularMarketPrice(dataDTO.getRegularMarketPrice()).type(type)
    //       .apiTimeStamp(currZonedDateTime)
    //       .build();

    //   tStocksPriceRepo.save(priceEntity);
    // }
    
      // ----save in redis------------------------------------------------------------
    //   List<TStockPriceEntity> priceLists = 
    //   yhFinanceService.getPriceCache();
    // }catch(Exception e){ 
    //   System.out.println("Error occurred while fetching stock data " + e.getMessage());
    //   e.printStackTrace();
    
    
    
   
 

   
  