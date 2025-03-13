package com.bc2412.demo_api.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bc2412.demo_api.lib.RedisManager;
import com.bc2412.demo_api.model.dto.OHLCWebDto;
import com.bc2412.demo_api.service.OHLCService;

@Service
public class OHLCServiceImpl implements OHLCService {
  @Value("${api.coingecko.coin-market.url.base}") // https://api.coingecko.com
  private String baseUrl;

  // api version
  @Value("${api.coingecko.coin-market.url.version}") // "api/v3"
  private String versionUrl;

  // !-------------------OHLC
  @Value("${api.coingecko.coin-market.url.coinsOHLCEndpoint}") // "/coins/bitcoin/ohlc"
  private String coinsOHLCEndpoint;
  // !---------------------------------------

  @Value("${api.coingecko.coin-market.param.vsCurrency}")
  private String vsCurrency;

  @Value("${api.coingecko.coin-market.param.ids:NULL}")
  private String ids;


  // !-------------------OHLC
  @Value("${api.coingecko.coin-market.param.days}")
  private String days;

  @Value("${api.coingecko.coin-market.param.x-cg-pro-api-key}")
  private String xCgProApiKey;
  // !---------------------------------------


  @Autowired
  private RestTemplate restTemplate;

  @Override
  public OHLCWebDto getOHLC() {
    String urlOHLC = UriComponentsBuilder.fromUriString(baseUrl)
        .pathSegment(versionUrl).path(coinsOHLCEndpoint)
        .queryParam("vs_currency", Optional.of(vsCurrency))
        .queryParam("ids", Optional.ofNullable(null))
        .queryParam("days", Optional.of(days))
        .queryParam("x-cg-pro-api-key", Optional.of(xCgProApiKey)).build()
        .toUriString();

    List<List<Object>> response =
        this.restTemplate.getForObject(urlOHLC, List.class);
    List<List<Double>> ohlcData = new ArrayList<>();

    for (List<Object> item : response) {
      List<Double> convertedItem = new ArrayList<>();
      for (Object value : item) {
        if (value instanceof Number) {
          convertedItem.add(((Number) value).doubleValue());
        }
      }
      ohlcData.add(convertedItem);
    }
    return new OHLCWebDto(ohlcData);
  }
}
