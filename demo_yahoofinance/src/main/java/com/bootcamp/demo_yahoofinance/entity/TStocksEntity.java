package com.bootcamp.demo_yahoofinance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "tstocks")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class TStocksEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String symbol; //"0388.HK"
 
 
 
  // public String[] split(String string) {
  //   // TODO Auto-generated method stub
  //   throw new UnsupportedOperationException("Unimplemented method 'split'");
  // }


  
}
