package com.bootcamp.demo_yahoofinance.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo_yahoofinance.entity.TStocksEntity;

@Repository
public interface TStocksRepository extends JpaRepository<TStocksEntity, Long>{

  public Optional<TStocksEntity> findBySymbol(String symbol);
  public List<TStocksEntity> findAll();
  public boolean existsBySymbol(String symol);

}