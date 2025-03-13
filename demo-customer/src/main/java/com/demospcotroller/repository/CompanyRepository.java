package com.demospcotroller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demospcotroller.entity.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long>{

  
} 
