package com.bootcamp.demo_yahoofinance.service.imp;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
      return new ArrayList<>(Arrays.asList(redisPrceList));// return directly
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









  //
//----------------------------------------------------------
// public List<TStockPriceEntity> getLastDataFromCacheOrDB(String symbol, ZonedDateTime yesterdayEnd)
// throws JsonProcessingException {
//   // 先從 Redis 獲取數據
//   List<TStockPriceEntity> priceData = redisManager.getLastPriceData(symbol, yesterdayEnd);
//   if (priceData.isEmpty()) {
//       // 如果 Redis 中沒有數據，則從數據庫獲取
//       return getPriceFromDB(symbol, yesterdayEnd);
//   }
//   return priceData; // 返回從 Redis 獲取的數據
// }
public TStockPriceEntity getLatestStockPrice(String symbol) throws IOException {
  // 從 Redis 獲取所有價格數據
  List<TStockPriceEntity> priceList = redisManager.getPriceCache();
  
  if (priceList != null) {
      // 選擇與給定 symbol 匹配的最新價格數據
      return priceList.stream()
          .filter(priceEntity -> priceEntity.getSymbol().equals(symbol)) // 根據 symbol 過濾
          .max(Comparator.comparing(TStockPriceEntity::getRegularMarketTime)) // 根據時間戳選擇最新的
          .orElse(null); // 如果沒有找到，返回 null
  }
  
  return null; // 如果 priceList 為 null，返回 null
}

//


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


 public TStockPriceEntity getStockPrice(String symbol) throws JsonProcessingException {
  try {
      // 首先從 Redis 獲取數據
      TStockPriceEntity priceEntity = redisManager.getPriceBySymbol(symbol);
      if (priceEntity != null) {
          return priceEntity; // 找到則返回
      }

      // 如果 Redis 中沒有，則從數據庫查詢
      priceEntity = tStocksPriceRepo.findBySymbol(symbol);
      if (priceEntity != null) {
          // 將從數據庫獲取的數據添加到 Redis 中
          List<TStockPriceEntity> priceList = redisManager.getPriceCache();
          if (priceList == null) {
              priceList = new ArrayList<>(); // 如果 Redis 中沒有價格列表，則創建一個新的列表
          }
          priceList.add(priceEntity); // 添加新價格實體
          redisManager.setPriceList(priceList); // 更新 Redis 中的價格列表
      }

      return priceEntity; // 返回查詢到的價格實體
  } catch (Exception e) {
      e.printStackTrace(); // 記錄異常
      return null; // 返回 null
  }
}





}