package com.bc2412.demo_api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bc2412.demo_api.dto.CryptoWebDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CryptoWebOperation { 
  @CrossOrigin
  @GetMapping("/coin/market")
  List<CryptoWebDTO> getCoinMarket();
 
  // @GetMapping(value = "/coins/markets")
  // @ResponseStatus(value = HttpStatus.OK)
  // List<CryptoWebDTO> markets (@RequestParam String currency,
  // @RequestParam(value = "ids", required = false) List<String> coins);⁡

  @CrossOrigin 
  @GetMapping("/cache/coin/market")
  List<CryptoWebDTO> getCoinMarketWithCache() throws JsonProcessingException;
}
