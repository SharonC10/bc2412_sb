package com.bootcamp.demo_yahoofinance.entity;

import java.time.ZonedDateTime;
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
@Table (name = "tstocksprice")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class TStockPriceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String symbol;
  private ZonedDateTime regularMarketTime; //Market Unix Time in normal timestamp
  private Double regularMarketPrice;
  private Double regularMarketChangePercent;
  private Double bid;
  private Double ask;
  private String type;
  private ZonedDateTime apiTimeStamp;

  // API Datetime (current time stamp)
  //TYPE ??? 


 
}