package com.bootcamp.demo.demo_sb_restful.Controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
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

@Controller //Controller 唔應該寫羅緝
@ResponseBody

public class CatController {
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

//insert  
 @PostMapping(value =  "/cat")
 public Cat createCat(@RequestBody Cat cat) {
     if (this.catService.put(cat)) //null pointer exception
     return cat;
     return null;
 }
 
  // @PostMapping(value = "/cat") //Post Mapping -> create things
  // public Cat createCat(@RequestBody Cat cat) {
  //     if (CatDatabase.put(cat))
  //     return cat;
  //     return null;
  // }
  

  // Arrays.asList() vs  List.of() vs new ArrayList<>()
  //Get all cats
  @PostMapping(value = "/cats")
  public List<Cat> getAllCat() {
      return List.of(CatDatabase.HOME);
  }
  
  //------------------------------------------
  //Get cat by id
  //https://localhost:8082/cat?id=1 

  //https://localhost:8082/cat?catId=1
  //Deserialization
  @GetMapping("/cat")
  //public Cat getCat(@RequestParam (value = "catId") Long id)
  public Cat getCat(@RequestParam Long id) {
      return CatDatabase.find(id).orElse(null);
  }
  
  //------------------------------------------
  //Get cat by name
  @GetMapping("/catname")
  public Cat getCatByName(@RequestParam String name) {
      return CatDatabase.getCatByName(name).orElse(null);
  }
  


  //------------------------------------------
  //Delete
  @DeleteMapping (value = "/cat")
    public Boolean deleteCat (@RequestParam Long id){
      return CatDatabase.delete(id);
    }



    //put (HashMap.put() -> if exists, override, otherwise, create new )

    @PutMapping(value = "/cat")
    public Boolean update(@RequestParam Long id, @RequestBody Cat cat) {
        return CatDatabase.update(id, cat);
    }


    @PatchMapping(value = "/cat/name/{name}")
    public Boolean patchCatName(@RequestParam Long id, @PathVariable String name){
      return CatDatabase.patchName(id, name);
    }



  }



