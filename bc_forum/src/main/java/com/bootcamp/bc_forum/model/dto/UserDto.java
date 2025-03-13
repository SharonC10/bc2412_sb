package com.bootcamp.bc_forum.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
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
  @NoArgsConstructor
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Geo {
      @JsonProperty(value = "lat")
      private String latitude;
      @JsonProperty(value = "lng")
      private String longitude;
    }
  }


  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }
}


