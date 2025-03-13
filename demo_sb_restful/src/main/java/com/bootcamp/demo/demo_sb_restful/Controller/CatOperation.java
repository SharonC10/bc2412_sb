package com.bootcamp.demo.demo_sb_restful.Controller;


import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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

public interface CatOperation {

@PostMapping(value =  "/cat")
public Cat createCat(@RequestBody Cat cat );


@GetMapping(value = "/cats")
  public List<Cat> getAllCat();

  @GetMapping(value = "/cat")
  public Cat getCat(@RequestParam Long id);

  @GetMapping("/catname")
  public Cat getCatByName(@RequestParam String name);
  
  @DeleteMapping (value = "/cat")
    public Boolean deleteCat (@RequestParam Long id);

    @PutMapping(value = "/cat")
    public Boolean update(@RequestParam Long id, @RequestBody Cat cat) ;

    @PatchMapping(value = "/cat/name/{name}")
    public Boolean patchCatName(@RequestParam Long id, @PathVariable String name);


  



}
