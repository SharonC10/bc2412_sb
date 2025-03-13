package com.bc2412.demo_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CryptoWebDTO {
  private String id;
  private String symbol;
  private String name;
  private String image;
  private Double currentPrice;
  private Integer marketCapRank;
  private Double totalVolume;
  private Double marketCap;
  private Double priceChange24h;
  private Double priceChangePercentage24h;
  private String lastUpdated;
}
