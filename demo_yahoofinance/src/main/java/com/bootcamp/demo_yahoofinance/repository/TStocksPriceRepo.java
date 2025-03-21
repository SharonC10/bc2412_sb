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
  public TStockPriceEntity findBySymbol(String symbol);

  @Query("SELECT t FROM TStockPriceEntity t WHERE t.symbol = :symbol AND t.apiTimeStamp >= :startTime ORDER BY t.apiTimeStamp")
    List<TStockPriceEntity> findOpeningData(@Param("symbol") String symbol, @Param("startTime") ZonedDateTime startTime);

    @Query("SELECT t FROM TStockPriceEntity t WHERE t.symbol = :symbol ORDER BY t.apiTimeStamp DESC")
    List<TStockPriceEntity> findLiveData(@Param("symbol") String symbol);

    @Query("SELECT t FROM TStockPriceEntity t WHERE t.symbol = :symbol AND t.apiTimeStamp <= :dateTime ORDER BY t.apiTimeStamp DESC")
    List<TStockPriceEntity> findByDateTime(@Param("symbol") String symbol, @Param("dateTime") ZonedDateTime dateTime);


}
