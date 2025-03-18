package com.bootcamp.demo_yahoofinance.service.imp;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.demo_yahoofinance.dto.StockListDTO;
import com.bootcamp.demo_yahoofinance.entity.TStockPriceEntity;
import com.bootcamp.demo_yahoofinance.entity.TStocksEntity;
import com.bootcamp.demo_yahoofinance.lib.RedisManager;
import com.bootcamp.demo_yahoofinance.repository.TStocksPriceRepo;
import com.bootcamp.demo_yahoofinance.repository.TStocksRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class YHFinanceService {
  @Autowired
  private RedisManager redisManager;
  @Autowired
  private TStocksRepository tStocksRepository;
  @Autowired
  private TStocksPriceRepo tStocksPriceRepo;


  // Redis get data for 24hrs
  // redis -> null ---> getQuoteData()
  public List<TStocksEntity> getStockListWithCache() //[][][]
  throws JsonProcessingException{
  TStocksEntity[] redisStockList =
  this.redisManager.get("STOCK-LIST", TStocksEntity[].class);
  if (redisStockList != null){//do i need Object Mapper? (json -> string)
  return Arrays.asList(redisStockList);//return directly
  }
  List<TStocksEntity> quoteStockLists = getStockListDB();
  this.redisManager.set("STOCK-LIST", quoteStockLists,Duration.ofHours(24));
  return quoteStockLists;
  }

  //-----------------------------------
  public List<TStocksEntity> getStockListDB(){
  List<TStocksEntity> stockListDB =
  this.tStocksRepository.findAll();

  return stockListDB;
  }

// redis data (null) ---> DB ----> set in Redis (PRICE-LIST})---------------------------
  public List<TStockPriceEntity> getPriceCache()
      throws JsonProcessingException {
    TStockPriceEntity[] redisPrceList =
        this.redisManager.get("PRICE-LIST", TStockPriceEntity[].class);
    if (redisPrceList != null) {
      return Arrays.asList(redisPrceList);// return directly
    }
    List<TStockPriceEntity> quotePriceLists = getPriceInDB();
    this.redisManager.set("PRICE-LIST", quotePriceLists, Duration.ofHours(24));
    return quotePriceLists;
  }

  public List<TStockPriceEntity> getPriceInDB() {
    List<TStockPriceEntity> stockPriceDB = this.tStocksPriceRepo.findAll();

    return stockPriceDB;
  }

//design what is yesterdayEnd
//get yesterday data at (16:59:59)
public TStockPriceEntity findLastDataForPreviousDay(){
  ZonedDateTime yesterdayEnd = 
  ZonedDateTime.now(ZoneId.of("Asia/Hong Kong"))
  .minusDays(1)
  .withHour(16)
  .withMinute(59)
  .withSecond(59)
  .withNano(0);

  return tStocksPriceRepo.findLastDataForPreviousDay(yesterdayEnd);
}





}
