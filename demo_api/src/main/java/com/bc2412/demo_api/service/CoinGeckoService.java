package com.bc2412.demo_api.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.bc2412.demo_api.model.CoinMarket;
import com.bc2412.demo_api.model.dto.OHLCWebDto;

public interface CoinGeckoService {
  List<CoinMarket> getCoinMarket();

  List<CoinMarket> getCoinMarketWithCache() throws JsonProcessingException;
  
}
