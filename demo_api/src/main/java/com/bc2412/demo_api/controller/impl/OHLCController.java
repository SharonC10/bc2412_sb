package com.bc2412.demo_api.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.bc2412.demo_api.controller.OHLCOperation;
import com.bc2412.demo_api.model.dto.OHLCWebDto;
import com.bc2412.demo_api.service.OHLCService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// http://localhost:8088/crypto/api/v3/coin/bitcoin
@RestController
@RequestMapping ("/crypto/api/v3")
public class OHLCController implements OHLCOperation{
  @Autowired
  private OHLCService ohlcService;

  @Override
  public OHLCWebDto getOhlcWebDtos(){
    return ohlcService.getOHLC();

  }
  
}
