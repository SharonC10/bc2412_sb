package com.bootcamp.web.demo_coin_web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.web.demo_coin_web.model.dto.CoinMarketDto;
import com.bootcamp.web.demo_coin_web.service.CoinService;


@Controller // return html
public class ViewController {
  @Autowired
  private CoinService coinService;

  @GetMapping(value = "/bootcamp")
  public String sayHelloPage(Model model) {
    model.addAttribute("tutor", "vincent");
    return "hello"; // html file name
  }

  //price change 24h 3.23% --> green
  //price change 24h -1.23% --> red
  @GetMapping(value = "/coin")
  public String sayHelloPage2(Model model) {
    List<CoinMarketDto> dtos = this.coinService.getCoinMarket();
    model.addAttribute("coinList", dtos);
    return "coin"; //html file name
  }
}
