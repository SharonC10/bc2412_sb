package com.bootcamp.demo_yahoofinance.lib;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo_yahoofinance.model.dto.QuoteResponseDto;

public class YahooFinanceManager {
  private RestTemplate restTemplate;
  private CrumbManager crumbManager;

  // Dependency injection
  public YahooFinanceManager(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    this.crumbManager = new CrumbManager(restTemplate);
  }

  // private static final String COOKIE = "B=12345abcde; GUC=AQEBCAFZ..."; // 你的 Cookie
  private static final String USER_AGENT = "Mozilla/5.0";

  public QuoteResponseDto quote(String symbol) {
    String dataUrl =
        "https://query1.finance.yahoo.com/v7/finance/quote?symbols=" + symbol
            + ".HK&crumb=" + crumbManager.getData();
    System.out.println("url=" + dataUrl);
    
    HttpEntity<String> entity = this.crumbManager.getHttpEntity();

    ResponseEntity<QuoteResponseDto> finalResponse = restTemplate
        .exchange(dataUrl, HttpMethod.GET, entity, QuoteResponseDto.class);
    return finalResponse.getBody();

  }


  public static void main(String[] args) {
    QuoteResponseDto y1 =
        new YahooFinanceManager(new RestTemplate()).quote("0388");
    // y1.getQuoteResponse().getResult().get(0).getCurrency();
    System.out.println(y1.getQuoteResponse().getResult().get(0).getRegularMarketPrice());

    QuoteResponseDto y2 =
        new YahooFinanceManager(new RestTemplate()).quote("0700");
    // y1.getQuoteResponse().getResult().get(0).getCurrency();
    System.out.println(y2.getQuoteResponse().getResult().get(0).getRegularMarketPrice());
   
    QuoteResponseDto y3 =
    new YahooFinanceManager(new RestTemplate()).quote("0005");
// y1.getQuoteResponse().getResult().get(0).getCurrency();
System.out.println(y3.getQuoteResponse().getResult().get(0).getRegularMarketPrice());
  
  }
   
  
}
