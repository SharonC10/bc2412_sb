package com.bootcamp.bc_forum.dto;

import java.util.List;
import com.bootcamp.bc_forum.model.dto.CommentDto;
import com.bootcamp.bc_forum.model.dto.UserDto.Address.Geo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@Setter
public class UserDTO {
  private Long id;
  private String name;
  private String username;
  private String email;
  private AddressDTO address;
  private String phone;
  private String website;
  private CompanyDTO company;
   @JsonProperty(value = "posts")
  private List<PostDTO> post;


  @Getter
  @AllArgsConstructor
  @Builder
  public static class AddressDTO {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;


    @Getter
    @AllArgsConstructor
    @Builder
    public static class GeoDTO {
      private Double lat;
      private Double lng;
    }
  }


  @Getter
  @AllArgsConstructor
  @Builder
  public static class CompanyDTO {
    private String name;
    private String catchPhrase;
    private String bs;
  }

  @Getter
  @AllArgsConstructor
  @Builder
  @Setter
  public static class PostDTO {
    private Long id;
    private String title ;
    private String body;
    @JsonProperty(value = "comments")
    private List<CommentDto> comment;

    @Getter
    @Builder
    @Setter
    @AllArgsConstructor
    public static class CommentDTO {
    private Long id;
    private String name ;
    private String email;
    private String body;
    }
  }
}
