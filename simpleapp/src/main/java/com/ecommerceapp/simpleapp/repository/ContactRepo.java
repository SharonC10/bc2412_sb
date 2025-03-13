package com.ecommerceapp.simpleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerceapp.simpleapp.entity.MessageEntity;

@Repository
public interface ContactRepo extends JpaRepository<MessageEntity, Long> {



}
