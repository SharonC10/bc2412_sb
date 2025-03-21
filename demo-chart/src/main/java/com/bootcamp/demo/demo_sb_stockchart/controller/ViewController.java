package com.bootcamp.demo.demo_sb_stockchart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

  @GetMapping("/linechart")
  public String linechart(Model model) {
    return "linechart";
  }

  @GetMapping("/candlechart")
  public String candlechart(Model model) {
    return "candlechart";
  }
}
