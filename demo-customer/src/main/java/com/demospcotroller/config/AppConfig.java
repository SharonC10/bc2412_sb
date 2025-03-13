package com.demospcotroller.config;

import java.math.BigDecimal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.client.RestTemplate;
import com.demospcotroller.codewave.RedisManager;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration //bean
public class AppConfig {
  //! More than one method for creating beans.
  @Bean
  BigDecimal bigDecimal(){
    return BigDecimal.valueOf(10);
  }

  @Bean
  String tutor(){
    return "Vincent";
  }// 通常唔會用String, 因為如果有兩個String 就run唔到
  // 可以開一個class
  
  @Bean 
  RestTemplate restTemplate(){   //???
    return new RestTemplate();
  }

  // key, value -> <String, String>
  // Spring find the parameter dependency automatically
  @Bean
  RedisManager redisManager(RedisConnectionFactory factory , ObjectMapper objectMapper){
    return new RedisManager(factory, objectMapper);
  }

  // RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
  //   RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    
  //   redisTemplate.setConnectionFactory(factory);
  //   redisTemplate.setKeySerializer(RedisSerializer.string());
  //   redisTemplate.setValueSerializer(RedisSerializer.json());
  //   redisTemplate.afterPropertiesSet();
  //   return redisTemplate;
  // }

  @Bean 
  ObjectMapper objectMapper(){
    return new ObjectMapper();
  }
}

//有人寫好左D class :e.g BigDecimal
//正常情況以試不會變成Bean
