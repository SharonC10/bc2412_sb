package com.bootcamp.demo.demo_sb_restful.Controller.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_restful.Controller.CatOperation;
import com.bootcamp.demo.demo_sb_restful.Model.Cat;
import com.bootcamp.demo.demo_sb_restful.Model.CatDatabase;
import com.bootcamp.demo.demo_sb_restful.Service.CatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


//!RESTful API -> Get/Post/Delete/Put/Patch
// (control signal resource by Get/Post/Delete/Put/Patch)

//Controller -> The ways to control Cat resource (in Model)
// insert , update, delete, select

// PostMapping ---> create 
// users give me id, name , age ---> create user 

// @Controller //Controller 唔應該寫羅緝
// @ResponseBody
@RestController
public class CatController implements CatOperation{
  //Controller is call Service  -> Service call catDatabase 
  //Dependency Injection (Spring Core Concept)

  //Frild Injecrtion 
  @Autowired //Autowired : try to find an object which fits into catService.
  //(Befoe server start complete )
  //! if fail, server start fail.
  private CatService catService;
//insert

//Constructor Injection
// @Autowired
// public CatController (CatService catService){
//   this.catService = catService;
// }


@Override
public Cat createCat( Cat cat ) {
 if (this.catService.put(cat)) //null pointer exception
    return cat;
    return null;
}
//insert  
//  @PostMapping(value =  "/cat")
//  public Cat createCat(@RequestParam Long id, @RequestParam String name, @RequestParam Integer age ) {
//      Cat cat = new Cat(id, name, age);
//   if (this.catService.put(cat)) //null pointer exception
//      return cat;
//      return null;
//  }

//  @PostMapping(value =  "/cat/{id}/{name}/{age}")
//  public Cat createCat(@PathVariable String name, @PathVariable Long id, @PathVariable Integer age ) {
//      Cat cat = new Cat(id, name, age);
//   if (this.catService.put(cat)) //null pointer exception
//      return cat;
//      return null;
//  }

 
//  @GetMapping("/cat")
//  public void create() {
//      return new Cat(1, "Sharon", 10);
//  }
 
 
  // @PostMapping(value = "/cat") //Post Mapping -> create things
  // public Cat createCat(@RequestBody Cat cat) {
  //     if (CatDatabase.put(cat))
  //     return cat;
  //     return null;
  // }
  
 
  // Arrays.asList() vs  List.of() vs new ArrayList<>()
  //Get all cats

  public List<Cat> getAllCat() {
      return Arrays.asList(CatDatabase.HOME);
  }
  
  //------------------------------------------
  //Get cat by id
  //http://localhost:8082/cat?id=1 

  
  //Deserialization
  @GetMapping("/cat")
  //public Cat getCat(@RequestParam (value = "catId") Long id)
  public Cat getCat( Long id) {
    new Cat(id, null, null);
      return CatDatabase.find(id).orElse(null);
  }
  //http://localhost:8082/cat?catId=1
  //------------------------------------------
  //Get cat by name
  public Cat getCatByName( String name) {
      return CatDatabase.getCatByName(name).orElse(null);
  }
  


  //------------------------------------------
  //Delete
    public Boolean deleteCat ( Long id){
      return CatDatabase.delete(id);
    }



    //put (HashMap.put() -> if exists, override, otherwise, create new )

    public Boolean update( Long id, @RequestBody Cat cat) {
        return CatDatabase.update(id, cat);
    }


    
    public Boolean patchCatName( Long id, @PathVariable String name){
      return CatDatabase.patchName(id, name);
    }



  }



