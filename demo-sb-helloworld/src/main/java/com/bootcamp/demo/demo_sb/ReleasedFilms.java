package com.bootcamp.demo.demo_sb;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder

public class ReleasedFilms {
  private String name ;
  private LocalDate releaseDate;

  public ReleasedFilms(String name, LocalDate releasDate){
    this.name = name;
    this.releaseDate = releasDate;
  }

  // public ReleasedFilms getReleasedFilms(){
  //   List<ReleasedFilms> releasedFilms = 
  //   Arrays.asList(new ReleasedFilms("film A",LocalDate.of(2022, 1, 1) )
  //   ,new ReleasedFilms("film B", LocalDate.of(2022, 1, 2)));
    
  //   return ;
  // }
}
