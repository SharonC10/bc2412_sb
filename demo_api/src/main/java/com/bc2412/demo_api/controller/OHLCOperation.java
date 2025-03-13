package com.bc2412.demo_api.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.service.annotation.GetExchange;
import com.bc2412.demo_api.model.dto.OHLCWebDto;

public interface OHLCOperation {
  @CrossOrigin
  @GetMapping("/coin/bitcoin")
  OHLCWebDto getOhlcWebDtos();
}
