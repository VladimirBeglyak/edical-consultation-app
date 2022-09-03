package com.itexpert.domain;

import javax.persistence.Entity;

@Entity
public class Patient extends UserAccount {

  public Patient() {
  }

  public Patient(Role role, String email, String password) {
    super(role, email, password);
  }
}
