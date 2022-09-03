package com.itexpert.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Doctor extends UserAccount {

  @Enumerated(EnumType.STRING)
  private Specialty specialty;

  public Doctor(Role role, String email, String password, Specialty specialty) {
    super(role, email, password);
    this.specialty = specialty;
  }

  public Doctor() {
  }

  public Specialty getSpecialty() {
    return specialty;
  }

  public void setSpecialty(Specialty specialty) {
    this.specialty = specialty;
  }
}
