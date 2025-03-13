package com.bootcamp.demo_yahoofinance.service.imp;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.demo_yahoofinance.entity.TStocksEntity;
import com.bootcamp.demo_yahoofinance.lib.RedisManager;
import com.bootcamp.demo_yahoofinance.repository.TStocksRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class YHFinanceService {
  @Autowired
  private RedisManager redisManager;
  @Autowired
  private TStocksRepository tStocksRepository;


  //Redis get data for 24hrs
  //redis -> null ---> getQuoteData()
public List<TStocksEntity> getStockListWithCache() //[][][]
throws JsonProcessingException{
  TStocksEntity[] redisStockList =
  this.redisManager.get("STOCK-LIST", TStocksEntity[].class);
  if (redisStockList != null){
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


}
