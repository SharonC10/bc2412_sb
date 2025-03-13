package com.bootcamp.web.demo_coin_web.service.impl;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.bootcamp.web.demo_coin_web.model.dto.CoinMarketDto;
import com.bootcamp.web.demo_coin_web.service.CoinService;

@Service
public class CoinServiceimpl implements CoinService{
  // @Autowired
  // private ModelMapper modelMapper;
  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<CoinMarketDto> getCoinMarket() {
    String url = "https://api.coingecko.com/api/v3/coins/markets?ids=bitcoin,ethereum,tether&vs_currency=usd";

    List<CoinMarketDto> coinMarketDtos = Arrays.asList(
        this.restTemplate.getForObject(url, CoinMarketDto[].class));
  return coinMarketDtos;
      }
}

