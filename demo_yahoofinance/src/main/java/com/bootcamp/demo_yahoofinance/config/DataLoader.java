package com.bootcamp.demo_yahoofinance.config;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bootcamp.demo_yahoofinance.entity.YHFinanceEntity;
import com.bootcamp.demo_yahoofinance.lib.RedisManager;
import com.bootcamp.demo_yahoofinance.repository.YHFinanceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataLoader implements CommandLineRunner {
  private final YHFinanceRepository yhFinanceRepository;
 
  @Autowired
  private RedisManager redisManager;

  public DataLoader(YHFinanceRepository yhFinanceRepositoryepository) {
    this.yhFinanceRepository = yhFinanceRepositoryepository;
  }
  

  @Override
  public void run(String... args) throws Exception {
    List<YHFinanceEntity> entities = new ArrayList<>();
    entities.add(new YHFinanceEntity(null, "0388.HK"));
    entities.add(new YHFinanceEntity(null, "0700.HK"));
    entities.add(new YHFinanceEntity(null, "0005.HK"));
    entities.add(new YHFinanceEntity(null, "0002.HK"));

    // ------------------------------------------------
    for (YHFinanceEntity entity : entities) {
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
    // -----------------------------------------------------
    for (YHFinanceEntity entity2 : entities) {
      // 將 entity 存入 Redis
      redisTemplate.opsForValue().set(entity2.getSymbol(), entity2,
          Duration.ofHours(24));
      System.out.println("STOKK-LIST" + entity2.getSymbol());
    }



  }

}
