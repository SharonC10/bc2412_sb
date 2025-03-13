package com.bc2412.demo_api.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bc2412.demo_api.model.CoinMarket;
import com.bc2412.demo_api.model.dto.CoinGeckoMarketDto;

@Component
public class CoreMapper {
  @Autowired
  private ModelMapper modelMapper;

  public CoinMarket map(CoinGeckoMarketDto dto) {
    return this.modelMapper.map(dto, CoinMarket.class);
  }
}
