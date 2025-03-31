package com.project.demo_form.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class User {
  private String userName;
  private String password;
  private String email;
  private char gender;
  private int phoneNumber;
 
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private Date birthday;
  private boolean marriedState;
}
