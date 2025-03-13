package com.bootcamp.web.demo_coin_web.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.web.demo_coin_web.model.dto.CoinMarketDto;

public interface CoinsOperation {
  
  @CrossOrigin
  @GetMapping(value = "/coins/markets")
  @ResponseStatus(value = HttpStatus.OK)
  List<CoinMarketDto> markets (@RequestParam String currency,
    @RequestParam(value = "ids", required = false)List<String>coins);
}
