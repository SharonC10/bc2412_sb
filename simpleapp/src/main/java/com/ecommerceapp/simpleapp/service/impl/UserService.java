package com.ecommerceapp.simpleapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerceapp.simpleapp.entity.UserEntity;
import com.ecommerceapp.simpleapp.repository.UserRepo;

@Service
public class UserService {
  @Autowired
  private UserRepo userRepo;

  public List<UserEntity> getAllUser() {
    return userRepo.findAll();
  }

  public UserEntity getUserById(Long id) {
    return userRepo.findById(id).orElseThrow(
        () -> new RuntimeException("User with id " + id + " not found"));
  }

  public void createUser(UserEntity user) {
    userRepo.save(user);
  }

  public void updateUser(UserEntity User) {
    userRepo.findById(User.getId()).orElseThrow(() -> new RuntimeException(
        "User with id " + User.getId() + " not found"));
    userRepo.save(User);
  }

  public void deleteUser(Long id) {
    userRepo.findById(id).orElseThrow(
        () -> new RuntimeException("User with id " + id + " not found"));
    userRepo.deleteById(id);
  }

  public UserEntity findUserByEmail(String email) {
    return userRepo.findByEmail(email);
  }

  public boolean verifyCredentials(String email, String password) {
    UserEntity User = userRepo.findByEmail(email);
    if (User.getPassword().equals(password)) {
      return true;
    }

    return false;
  }


}
