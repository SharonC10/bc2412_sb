package com.bootcamp.demo_yahoofinance.lib;

import java.time.Duration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
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
    return objectMapper.readValue(json, clazz); //become json
  }

  public void set(String key, Object object, Duration duration)
  throws JsonProcessingException{
    String serializedJson = objectMapper.writeValueAsString(object); //json -> Object (String)
    this.redisTemplate.opsForValue().set(key, serializedJson,duration); //json-> String and save for 1hours
  }
}
