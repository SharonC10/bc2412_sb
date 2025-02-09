package com.bootcamp.demo.demo_sb_restful.Service;

import org.springframework.stereotype.Service;
import com.bootcamp.demo.demo_sb_restful.Model.Cat;
import com.bootcamp.demo.demo_sb_restful.Model.CatDatabase;

@Service //Bean
public class CatService {
//Service 無狀態 , stateless object can be 
// if no attribute = stateless 
//不可變路程 
// not only can use in Dog , Chicken ---> they all the same rode

//e.g: private int x ---> 加if 
   public  boolean put(Cat cat) {
    for (int i = 0; i < CatDatabase.HOME.length; i++) { // loop
      if (CatDatabase.HOME[i] == null) { // if the arrays have space
        // HOME[0] == nothing then put the Cat inside
        CatDatabase.HOME[i] = cat;
        return true; // ---> can put the Cat
      }
    }
    return false;
  }
}

//Person.class -> weight/height -> bmi() 


//it still have bug..what if more than one person call this method? 
// they can found space in HOME[0] and put 
