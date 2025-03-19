package com.bootcamp.demo_yahoofinance.lib;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import com.bootcamp.demo_yahoofinance.entity.TStockPriceEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisManager {
  private static final Duration DEFAULT_DURATION = Duration.ofHours(24);
  
  private RedisTemplate<String , String> redisTemplate;
  private ObjectMapper objectMapper;

  public RedisManager(RedisConnectionFactory factory, ObjectMapper objectMapper){
    this.redisTemplate = new RedisTemplate<>();
    this.redisTemplate.setConnectionFactory(factory);
    this.redisTemplate.setKeySerializer(RedisSerializer.string());//set key -> String
    this.redisTemplate.setValueSerializer(RedisSerializer.json());//set value-> json
    this.redisTemplate.afterPropertiesSet();
    this.objectMapper = objectMapper;
  }

  public <T> T get(String key, Class<T> clazz) throws JsonProcessingException{
    String json = this.redisTemplate.opsForValue().get(key);//get String ->json
    if (json == null){
      return null;
    }
    
    return objectMapper.readValue(json, clazz); //become json
  }

  public void set(String key, Object object, Duration duration)
  throws JsonProcessingException{
    String serializedJson = objectMapper.writeValueAsString(object); //json -> Object (String)
    this.redisTemplate.opsForValue().set(key, serializedJson,duration); //json-> String and save for 1hours
  }

  public void clearAll(){
    Set <String> keys = redisTemplate.keys("PRICE-LIST:*");
    if (keys != null && !keys.isEmpty()){
      redisTemplate.delete(keys);
    } 
  }

  // --------------------------------------------------------
  public <T> List<T> getLastPriceData(String symbol, ZonedDateTime yesterdayEnd)
  throws JsonProcessingException{
    String json = this.redisTemplate.opsForValue().get("PRICE-LIST:" +
    symbol + ":" + yesterdayEnd);
    if (json != null ){ 
      return objectMapper.readValue
      (json, objectMapper.getTypeFactory()
      .constructCollectionType(List.class, TStockPriceEntity.class));
    }
    return List.of();
  }


  
}
