package com.demospcotroller.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

//This DTO id for deserialization (JSON -> Object)
// why having dto -> UserDto? 
// save the attribute of the origianla version(in case 
// we might cut some attribute --> to your boss requir information)
@Getter
@Builder
public class UserDto { // ! Dto -> data transfer object
  private Long id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;

  @Getter
  @Builder
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Getter
    @Builder
    public static class Geo {
      @JsonProperty(value = "lat")
      private String latitude;
      @JsonProperty(value = "lng")
      private String longitude;
    }
  }

  @Getter
  @Builder
  public static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }
}
