package com.bootcamp.demo_restiful.controller;
//Controller -> The ways to control Cat resource
// insrert , update , delete ,select

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.bootcamp.demo_restiful.model.Cat;
import com.bootcamp.demo_restiful.model.CatDatabase;

public class CatController {
  // insert 
  @PostMapping(value = "/cat")
  public Cat createCat(@RequestBody Cat cat){
    if (CatDatabase.put(cat))
    return cat;
    return null;
  }
}
