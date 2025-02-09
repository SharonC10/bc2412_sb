package com.bootcamp.demo.demo_sb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

//Java Object -> JSON (Serialization)
// JSON -> Java Object (Deserialization)
// JSON means lan

//List vs Array (JSON)

// Attribute has no ordering in JSON

@Controller // @GetMapping
@ResponseBody // JSON
public class HelloworldController {
  // An API for Getting Resource 
  @GetMapping (value = "/greeting") //unique
  // usaully none ,it is API should be unique
  public String hello(){
    return "Hello World!";
  }


  // create another API to return an Integer
  @GetMapping(value = "/numbering") 
  public int getNumber() { 
      return 1+2 ;
  }

  //---------------------------------
  @GetMapping(value = "/integerArray") //integerArray
  public int[] getIntegerArray() {
   int [] num = new int[2];
   num[0] = 10;
   num[1] = 20;
   
   return num ;
  }

  @GetMapping(value = "/doubleArray")
  public double[] getDoubleArray() {
    double [] num2 = new double[]{10,100,200};
    double [] ans = new double[10];

   for (int i = 0;i< num2.length;i++){
    ans = num2;
   }
      return ans;
  }
  
  //Vincent ans
  // create another API to return an Integer Array
  @GetMapping(value = "/integers")
  public Integer[] getIntegers(){
    return new Integer[]{3,100,2}; 
  }
  

  //---------------------------------
  // create another API to return an List of String 
  @GetMapping(value = "listOfString")
  public List<String> getListOfString() {
    List <String> str = Arrays.asList("Sharon", "Vincent");
    return str;
  }

  //Vincent ans
  // Create another API to return List of String
  @GetMapping(value = "/strings")
  public List<String> getStrings() {
    return Arrays.asList("vincent", "lucas");
  }

  
  //---------------------------------
  // create another API to return an a Cat
  @GetMapping(value = "/cat")
  public Cat getCat(Cat cat) {
      return new Cat("Vincent", 10);
  }
  

  //---------------------------------
  // create another API to return an a List of Cat
  @GetMapping("/cats")
  public List<Cat> getCats() {
      return Arrays.asList(new Cat("Vincent", 10), new Cat("Sharon", 20));
  }
  
  //---------------------------------
  //create another API to return a of LocalDate
 @GetMapping("/localdate")
 public LocalDate getDate() {
     return LocalDate.of(2024, 2, 7);
 }
 
 
  //---------------------------------
  // @GetMapping(value = "/shoppingmall")
  // public ShoppingMall getShoppingMall() {
  //   ShoppingMall.Cinema.ReleasedFilms film1 = ReleasedFilms.builder()
  //   .name("film A")
  //   .releaseDate(LocalDate.of(2022, 1, 1)),
  //   .build();
  //   Cinema cinema = Cinema.builder()
  //   .releasedFilms()
  //   .name("ABC")
  //   .builder()
    
  //     return ShoppingMall.builder();
  // }
  
}
