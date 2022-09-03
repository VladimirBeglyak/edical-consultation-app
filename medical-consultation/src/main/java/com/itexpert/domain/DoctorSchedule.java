package com.itexpert.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;

@Entity
public class DoctorSchedule extends Schedule {

  public DoctorSchedule(UserAccount userAccount, LocalDateTime createAt) {
    super(userAccount, createAt);
  }

  public DoctorSchedule() {
  }
}
