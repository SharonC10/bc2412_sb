package com.bc2412.demo_api.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bc2412.demo_api.controller.CryptoWebOperation;
import com.bc2412.demo_api.dto.CryptoWebDTO;
import com.bc2412.demo_api.dto.mapper.DTOMapper;
import com.bc2412.demo_api.service.CoinGeckoService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;

// http://localhost:8088/crypto/api/v1/coin/market
@RestController
@RequestMapping("/crypto/api/v1")
public class CoinController implements CryptoWebOperation {
  @Autowired
  private CoinGeckoService coinGeckoService;

  @Autowired
  private DTOMapper dtoMapper;

  @Override
  public List<CryptoWebDTO> getCoinMarket(){
    return coinGeckoService.getCoinMarket() //
        .stream() //
        .map(e -> dtoMapper.map(e)) //
        .collect(Collectors.toList());
  }

  @Override
  public List<CryptoWebDTO> getCoinMarketWithCache() throws JsonProcessingException {
    return coinGeckoService.getCoinMarketWithCache() //
        .stream() //
        .map(e -> dtoMapper.map(e)) //
        .collect(Collectors.toList());
  }
}
