package com.bootcamp.demo_yahoofinance.config;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bootcamp.demo_yahoofinance.entity.TStocksEntity;
import com.bootcamp.demo_yahoofinance.lib.RedisManager;
import com.bootcamp.demo_yahoofinance.repository.TStocksRepository;
import com.bootcamp.demo_yahoofinance.service.imp.YHFinanceService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataLoader implements CommandLineRunner {
  private final TStocksRepository yhFinanceRepository;
  @Autowired
  private RedisManager redisManager;
  @Autowired
  private YHFinanceService yhFinanceService;

  public DataLoader(TStocksRepository yhFinanceRepositoryepository) {
    this.yhFinanceRepository = yhFinanceRepositoryepository;
  }


  @Override
  public void run(String... args) throws Exception {
    
    

    List<TStocksEntity> entities = new ArrayList<>();
    entities.add(new TStocksEntity(null, "0388.HK"));
    entities.add(new TStocksEntity(null, "0700.HK"));
    entities.add(new TStocksEntity(null, "0005.HK"));
    entities.add(new TStocksEntity(null, "0002.HK"));

    // ------------------------------------------------
    for (TStocksEntity entity : entities) {
      // 檢查 symbol 是否存在
      boolean exists = yhFinanceRepository.existsBySymbol(entity.getSymbol());
      if (!exists) {
        // 如果不存在，則保存該 entity
        yhFinanceRepository.save(entity);
        System.out.println("Saved entity with symbol: " + entity.getSymbol());
      } else {
        System.out.println(
            "Entity with symbol " + entity.getSymbol() + " already exists.");
      }
    }

    List<TStocksEntity> stockList = yhFinanceService.getStockListWithCache();
    
    
            }

}
