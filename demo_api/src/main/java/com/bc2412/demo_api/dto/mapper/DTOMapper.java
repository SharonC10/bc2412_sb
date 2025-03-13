package com.bc2412.demo_api.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bc2412.demo_api.dto.CryptoWebDTO;
import com.bc2412.demo_api.model.CoinMarket;

@Component
public class DTOMapper {
  @Autowired
  private ModelMapper modelMapper;

  public CryptoWebDTO map(CoinMarket coinMarket) {
    return this.modelMapper.map(coinMarket, CryptoWebDTO.class);
  }
}
