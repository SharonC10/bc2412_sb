package com.demospcotroller.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Convention Over Configuration

// ! Entity( Define Table Structure)
// ! Hibernate -> rely on provided driver, gernerate corresponding SQL (DDL)
//! mvn install -> test -> create table 
// PK, FK, auto_increment, column name, field size
@Entity
@Table (name = "Customers")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PersonEntity {
  @Id //->Primary Key
  @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
  private Long id;
  @Column(name = "customer_name")
  private String name;
  @Column(name = "customer_email")
  private String email;
}
