package com.ecommerceapp.simpleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ecommerceapp.simpleapp.entity.UserEntity;
import jakarta.transaction.Transactional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

  public UserEntity findByEmail(String email);

  @Transactional
  @Query(value = "SELECT setval(users_id_seq, (SELECT MAX(id) FROM users))",
      nativeQuery = true)
  void resetUserSequence();

}
