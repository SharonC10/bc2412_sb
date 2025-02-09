package com.bootcamp.demo.demo_sb_restful.Model;

import java.util.Optional;

public class CatDatabase {
  public static final Cat[] HOME = new Cat[5];

  // put the Cat (id ,name, age) in arrays
  // public static boolean put(Cat cat) {
  //   for (int i = 0; i < HOME.length; i++) { // loop
  //     if (HOME[i] == null) { // if the arrays have space
         // HOME[0] == nothing then put the Cat inside
  //       HOME[i] = cat;
  //       return true; // ---> can put the Cat
  //     }
  //   }
  //   return false;
  // }
  // ------------------------------------------
  // public static Cat find(Long catId){
  // for (int i = 0 ; i< HOME.length; i++){
  // for (Cat cat : HOME){
  // if (cat.getId() == catId)
  // return cat;
  // }
  // return null;
  // }

  // find the Cat
  // insert Cat id -> find cat
  public static Optional<Cat> find(Long catId) {
    // Optional -> present is not null
    for (Cat cat : HOME) {
      if (cat.getId() == catId) // !if cat's id == catId -> cat
        return Optional.of(cat);
    }
    return Optional.empty();// otherwise is empty
  }

  // find by name
  public static Optional<Cat> getCatByName(String catName){
    for (Cat searchCat: HOME){
      if (searchCat.getName() == catName){
        return Optional.of(searchCat);
      }
    }return Optional.empty();
  }
  // ------------------------------------------
  // delete the cat
  // e.g Cat (1, "Sharon", 10) delete

  public static Boolean delete(Long catId) { // delete by id
    for (int i = 0; i < HOME.length; i++) {
      if (HOME[i].getId() == catId) { // if HOME[i].getId == catId
        HOME[i] = null; // make it be null
        return true;
      }
    }
    return false;
  }

  // update the cat information by cat id and
  public static Boolean update(Long catId, Cat cat) {
    // find the cat id and which cat?
    // original : cat0, 1, "Vincent", 10
    // e.g id = 1 , and want to change the Cat cat1 (1, "Sharon", 10)
    for (int i = 0; i < HOME.length; i++) {
      if (HOME[i].getId() == catId) {
        // found id:1
        HOME[i] = cat; // HOME[0] = cat1
        return true;
      }
    }
    return false;
  }


  // !1 don't create cat, we should find the cat object , call setNaem()
  // !2. Other vales of this cat object remain unchanged.

  public static Boolean patchName(Long catId, String catName) {
    for (Cat cat : HOME) {
      if (cat.getId() == catId)
        cat.setName(catName);
      return true;
    }
    return false;
  }


  // find name
}

