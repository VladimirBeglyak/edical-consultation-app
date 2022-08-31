package com.itexpert.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class DoctorSchedule extends BaseSchedule {

  @OneToOne
  @JoinColumn(name = "doctor_id")
  private Doctor doctor;

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }
}
