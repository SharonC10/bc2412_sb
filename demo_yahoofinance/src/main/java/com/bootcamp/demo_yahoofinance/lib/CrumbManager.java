package com.bootcamp.demo_yahoofinance.lib;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.List;

public class CrumbManager {
  private RestTemplate restTemplate;
  private CookieManager cookieManager;
  private HttpEntity<String> httpEntity;

  public CrumbManager(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    this.cookieManager = new CookieManager(restTemplate);
  }

  // 這裡的 cookie 需要在你的環境測試是否有效
  // private static final String COOKIE = "B=12345abcde; GUC=AQEBCAFZ..."; // 你的 Cookie
  // private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
  // public static HttpEntity<String> entity = null;

  // !get the key with cookie --> suppose cant get anthing. show too much request (as the web know you re 'postman cookie')
  // public String getKey() {
  // String url = "https://query1.finance.yahoo.com/v1/test/getcrumb";

  // String key =
  // this.restTemplate.getForObject(url, String.class);
  // return key;
  // }

  // //!get key without cookie --->can show the key (we pretant we a the boswer)
  // @GetMapping (value = "/getkey")
  // public String getKeyNoCookie() {

  // // 獲取新 key
  // String url = "https://query1.finance.yahoo.com/v1/test/getcrumb";

  // HttpHeaders headers = new HttpHeaders();
  // headers.set(HttpHeaders.USER_AGENT, USER_AGENT);

  // HttpEntity<String> entity = new HttpEntity<>(headers);

  // ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

  // return response.getBody();

  // }

  // ------------------------------------
  // public String getKeyFcCookie() {
  // String url = "https://fc.yahoo.com";

  // // 設定 request headers
  // HttpHeaders headers = new HttpHeaders();
  // headers.set("User-Agent", "Mozilla/5.0"); //set own headers

  // entity = new HttpEntity<>(headers);

  // List<String> cookies = null;
  // try {
  // ResponseEntity<String> response =
  // restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
  // System.out.println("Response body: " + response.getBody());
  // System.out.println("Response headers: " + response.getHeaders());
  // } catch (HttpClientErrorException | HttpServerErrorException ex) {
  // cookies = ex.getResponseHeaders().get(HttpHeaders.SET_COOKIE);//？？
  // }

  // //! use https://fc.yahoo.com headers to set into
  // //! https://query1.finance.yahoo.com/v1/test/getcrumb headers

  // String crumbUrl = "https://query1.finance.yahoo.com/v1/test/getcrumb";
  // // headers.set("User-Agent",
  // // "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
  // headers.set("Cookie", String.join("", cookies));

  // // HttpEntity<String> entity2 = new HttpEntity<>(headers);

  // ResponseEntity<String> crumbResponse =
  // this.restTemplate.exchange(crumbUrl, HttpMethod.GET, entity, String.class);
  // String crumb = crumbResponse.getBody();
  // System.out.println(crumb);

  // return crumbResponse.getBody();
  // }

  // crump ---> Cookie
  // Yahoo ----> cookie
  // rest Template -> call api (call api need headers)

  public HttpEntity<String> getHttpEntity() {
    return this.httpEntity;
  }

  public String getData() {
    // i want this url ---> use cookieManager's cookies
    String crumbUrl = "https://query1.finance.yahoo.com/v1/test/getcrumb";
    HttpHeaders headers = new HttpHeaders();
    headers.set("User-Agent", "Mozilla/5.0");
    String cookieStr = this.cookieManager.getFcCookie();
    System.out.println("cookie=" + cookieStr);
    headers.set("Cookie", cookieStr);

    HttpEntity<String> entity = new HttpEntity<>(headers);
    this.httpEntity = entity; // !!!!

    ResponseEntity<String> crumpResponse = this.restTemplate.exchange(crumbUrl,
        HttpMethod.GET, entity, String.class);
    String crumb = crumpResponse.getBody();
    System.out.println("crumb=" + crumb);
    return crumb;
  }

  public static void main(String[] args) {
    CrumbManager crumbManager = new CrumbManager(new RestTemplate());
    String crumb = crumbManager.getData();
    System.out.println(crumb);
  }
}



// https://query1.finance.yahoo.com/v1/test/getcrumb
