package com.itexpert.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;

@Entity
public class PatientSchedule extends Schedule {

  private String description;

  public PatientSchedule(UserAccount userAccount, LocalDateTime createAt, String description) {
    super(userAccount, createAt);
    this.description = description;
  }

  public PatientSchedule() {
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
