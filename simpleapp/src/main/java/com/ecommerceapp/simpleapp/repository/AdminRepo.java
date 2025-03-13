package com.ecommerceapp.simpleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ecommerceapp.simpleapp.entity.AdminEntity;
import jakarta.transaction.Transactional;

@Repository
public interface AdminRepo extends JpaRepository<AdminEntity, Long> {

  public AdminEntity findByEmail(String email);

  @Transactional
  @Query(
      value = "SELECT setval(pg_get_serial_sequence('admins', 'id'), (SELECT MAX(id) FROM admins))",
      nativeQuery = true)
  void resetAdminSequence();

}
