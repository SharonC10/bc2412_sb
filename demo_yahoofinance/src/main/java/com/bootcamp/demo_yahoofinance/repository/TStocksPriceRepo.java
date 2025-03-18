package com.bootcamp.demo_yahoofinance.repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo_yahoofinance.entity.TStockPriceEntity;
import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface TStocksPriceRepo extends JpaRepository<TStockPriceEntity,Long>{

  public List<TStockPriceEntity> findAll();
  public Optional<TStockPriceEntity> findBySymbol(String symbol);

  @Query ("SELECT t FROM TStockPriceEntity t " + 
  "WHERE t.regularMarketTime < :yesterdayEnd " +
  "ORDER BY t.regularMarketTime DESC")
  TStockPriceEntity findLastDataForPreviousDay (@Param ("yesterdatEnd")
  ZonedDateTime yesterdayEnd);


  


}
