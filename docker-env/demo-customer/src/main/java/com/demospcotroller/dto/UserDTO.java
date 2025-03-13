package com.demospcotroller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

// Feature of DTO
//! 1. Different numbers of fields for the API
//! 2. Different field names
//This DTO is for serialization (Object -> JSON)
@Getter
@Builder
@AllArgsConstructor
public class UserDTO {
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
  @AllArgsConstructor
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Geo {
      @JsonProperty(value = "x") 
      private String latitude;
      @JsonProperty(value = "y")
      private String longitude;
    }
  }

  @Getter
  public static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }

 
}
