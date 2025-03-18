package com.bootcamp.demo_yahoofinance.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import com.bootcamp.demo_yahoofinance.dto.DataDTO;
import com.bootcamp.demo_yahoofinance.model.dto.QuoteResponseDto;

@Component
public class CoreMapper {
  @Autowired
  private ModelMapper modelMapper;

  public DataDTO map(QuoteResponseDto dto){
    return this.modelMapper.map(dto, DataDTO.class);
  }

}
