package com.bootcamp.demo_restiful.model;

public class CatDatabase {
  public static final Cat[] HOME = new Cat [5];

  public static boolean put(Cat cat){
    for (int i = 0; i < HOME.length; i++){
      if (HOME[i] == null ){
        return true;
      }
    }
    return false;
  }
}
