package com.demospcotroller.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "Users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserEntity {
  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String username;
  private String email;
  // private AddressEntity address;
  private String phone;
  private String website;
  // private CompanyEntity company;

  }

