package com.bootcamp.demo_yahoofinance.service.imp;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
  Optional<TStocksEntity[]> redisStockList = 
  Optional.ofNullable(
    this.redisManager.get("STOCK-LIST", TStocksEntity[].class));
  
  if (redisStockList.isPresent()){//do i need Object Mapper? (json -> string)
  return Arrays.asList(redisStockList.get());//return directly
  }//否則
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
  
//----------------------------------------------------------
  public List<TStockPriceEntity> getPriceInDB() {
    List<TStockPriceEntity> stockPriceDB = this.tStocksPriceRepo.findAll();

    return stockPriceDB;
  }
//----------------------------------------------------------
public List<TStockPriceEntity> getLastDataFromCacheOrDB(String symbol, ZonedDateTime yesterdayEnd)
throws JsonProcessingException {
  // 先從 Redis 獲取數據
  List<TStockPriceEntity> priceData = redisManager.getLastPriceData(symbol, yesterdayEnd);
  if (priceData.isEmpty()) {
      // 如果 Redis 中沒有數據，則從數據庫獲取
      return getPriceFromDB(symbol, yesterdayEnd);
  }
  return priceData; // 返回從 Redis 獲取的數據
}



 public List<TStockPriceEntity> getPriceFromDB(String symbol, ZonedDateTime dateTime) {
  return tStocksPriceRepo.findByDateTime(symbol, dateTime);
   // 根據時間和股票標識符查詢數據
}



public void refreshOpeningData(String symbol) throws JsonProcessingException {
  redisManager.clearAll();

  // 設定當天的開市時間
  ZonedDateTime startTime = ZonedDateTime.now(ZoneId.of("Asia/Hong_Kong")).withHour(9).withMinute(30).withSecond(0);
  
  // 獲取今天的開市數據
  List<TStockPriceEntity> openingData = getOpeningData(symbol, startTime);
  
  // 將開市數據存入 Redis
  redisManager.set("PRICE-LIST", openingData, Duration.ofHours(24));
}






public List<TStockPriceEntity> getOpeningData(String symbol, ZonedDateTime startTime) {
  return tStocksPriceRepo.findOpeningData(symbol, startTime); // 根據股票標識符和開市時間查詢數據
}

 public List<TStockPriceEntity> getLiveData(String symbol){ 
  return tStocksPriceRepo.findLiveData(symbol);
 }






}
