package com.demospcotroller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demospcotroller.entity.GeoEntity;

@Repository
public interface GeoRepository extends JpaRepository<GeoEntity, Long> {
  
}
