package com.demospcotroller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demospcotroller.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
  
}


