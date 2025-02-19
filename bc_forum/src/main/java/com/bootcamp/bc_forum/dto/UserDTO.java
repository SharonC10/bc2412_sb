package com.bootcamp.bc_forum.dto;

import java.util.List;
import com.bootcamp.bc_forum.model.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
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
  private List<Post> post;


  @Getter
  @AllArgsConstructor
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;


    @Getter
    @AllArgsConstructor
    public static class Geo {
      private Double lat;
      private Double lng;
    }
  }


  @Getter
  @AllArgsConstructor
  public static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }

  @Getter
  @AllArgsConstructor
  public static class Post {
    private Long id;
    private String title ;
    private String body;
    private List<CommentDto> comment;

    @Getter
    public static class Comment {
    private Long id;
    private String name ;
    private String email;
    private String body;
    }
  }
}
