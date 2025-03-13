package com.bootcamp.demo_yahoofinance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo_yahoofinance.lib.RedisManager;
import com.bootcamp.demo_yahoofinance.lib.YahooFinanceManager;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    YahooFinanceManager yahooFinanceManager() {
        return new YahooFinanceManager(restTemplate());
    }

    @Bean
    RedisManager redisManager(RedisConnectionFactory factory, ObjectMapper objectMapper ){
        return new RedisManager(factory, objectMapper);
    }

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
