package com.bootcamp.web.demo_coin_web.service;

import java.util.List;
import com.bootcamp.web.demo_coin_web.model.dto.CoinMarketDto;

public interface CoinService {
  List<CoinMarketDto> getCoinMarket();
}
