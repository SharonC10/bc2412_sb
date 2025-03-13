package com.demospcotroller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demospcotroller.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}

// What is Respository? 
// = SQL select all...insert into...search by id....find all....
