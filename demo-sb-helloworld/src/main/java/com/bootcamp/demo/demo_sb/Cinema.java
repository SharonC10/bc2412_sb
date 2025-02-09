package com.bootcamp.demo.demo_sb;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder

public class Cinema {
  private String name;
  private LocalDate openedDate;
  private Film [] releasedFilms;

  public Cinema (String name , LocalDate openedDate, Films [] releasedFilms){
    this.name = name;
    this.openedDate = openedDate;
    this.releasedFilms = releasedFilms;
  }

}
