package com.bootcamp.demo.demo_sb_restful.Controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
// Controller -> 交代客人要嘅野 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


  @RestController
public class BeanController {
  @GetMapping (value = "/beans")
  public List<String> getBeans() {
    return Arrays.asList(DemoSbRestfulApplication.context.getBeanDefinitionNames());
  }
}
