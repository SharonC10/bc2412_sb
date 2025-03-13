package com.bootcamp.demo_yahoofinance.lib;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class CookieManager {
  private RestTemplate restTemplate;

  public CookieManager(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  // public String getFcCookie(){
  // String url = "https://fc.yahoo.com";
  // //set Headers ---> being bower
  // HttpHeaders headers = new HttpHeaders();
  // headers.set("User-Agent", "Mozilla/5.0");
  // HttpEntity<String> entity = new HttpEntity<>(headers);

  // // set what is cookie --> null
  // List<String> cookies = null;
  // try {
  // ResponseEntity<String> response =
  // restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
  // System.out.println("Response body: " + response.getBody());
  // System.out.println("Response headers: " + response.getHeaders());
  // } catch (HttpClientErrorException | HttpServerErrorException ex) {
  // cookies = ex.getResponseHeaders().get(HttpHeaders.SET_COOKIE);//？？
  // }

  // return cookies.toString();
  // }
  public String getFcCookie() {
    String url = "https://fc.yahoo.com";
    HttpHeaders headers = new HttpHeaders();
    headers.set("User-Agent", "Mozilla/5.0");
    HttpEntity<String> entity = new HttpEntity<>(headers);

    List<String> cookies = null;
    try {
      ResponseEntity<String> response =
          this.restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
      System.out.println("Response body: " + response.getBody());
      System.out.println("Response headers: " + response.getHeaders());

      cookies = response.getHeaders().get(HttpHeaders.SET_COOKIE);
    } catch (HttpClientErrorException | HttpServerErrorException ex) {
      cookies = ex.getResponseHeaders().get(HttpHeaders.SET_COOKIE);
    }
    return cookies != null ? cookies.get(0).split(";")[0] : "";
  }

  public static void main(String[] args) {
    CookieManager cookieManager = new CookieManager(new RestTemplate());
    String cookies = cookieManager.getFcCookie();
    System.out.println("cookie =" + cookies);
  }
}
