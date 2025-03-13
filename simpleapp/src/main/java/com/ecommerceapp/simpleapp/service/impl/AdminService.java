package com.ecommerceapp.simpleapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerceapp.simpleapp.entity.AdminEntity;
import com.ecommerceapp.simpleapp.repository.AdminRepo;

@Service
public class AdminService {

  @Autowired
  private AdminRepo adminRepo;

  public List<AdminEntity> getAllAdmin() {
    return adminRepo.findAll();
  }

  public AdminEntity getAdminById(Long id) {
    return adminRepo.findById(id).orElseThrow(
        () -> new RuntimeException("Admin with id " + id + " not found"));
  }

  public void createUser(AdminEntity admin) {
    adminRepo.save(admin);
  }

  public void updateAdmin(AdminEntity admin) {
    adminRepo.findById(admin.getId()).orElseThrow(() -> new RuntimeException(
        "Admin with id " + admin.getId() + " not found"));
    adminRepo.save(admin);// if don't have this admin object -> save the admin just got.
  }

  public void deleteAdmin(Long id) {
    adminRepo.findById(id).orElseThrow(
        () -> new RuntimeException("Admin with id " + id + " not found"));
    adminRepo.deleteById(id);
  }

  public boolean verifyCredentials(String email, String password) {
    AdminEntity admin = adminRepo.findByEmail(email);
    // if the email = from the database ->true
    if (admin.getPassword().equals(password)) {
      return true;
    }

    return false;
  }

}
