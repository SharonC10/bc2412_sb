package com.demospcotroller.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component //bean
public class PreServerStartConfig implements CommandLineRunner{
  @Override
  public void run(String... args) throws Exception{
     // call JPH users api
    // call JPH post api
    // call JPH comment api
    // save DB
  }
}
