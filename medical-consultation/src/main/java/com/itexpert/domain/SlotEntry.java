package com.itexpert.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class SlotEntry {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  @JoinColumn(name = "doctor_id")
  private Doctor doctor;

  @OneToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;

  private LocalDateTime startTime;

  @Enumerated(EnumType.STRING)
  private SlotEntryStatusType statusType;

  public SlotEntry(Doctor doctor, Patient patient, LocalDateTime startTime, SlotEntryStatusType statusType) {
    this.doctor = doctor;
    this.patient = patient;
    this.startTime = startTime;
    this.statusType = statusType;
  }

  public SlotEntry() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public SlotEntryStatusType getStatusType() {
    return statusType;
  }

  public void setStatusType(SlotEntryStatusType statusType) {
    this.statusType = statusType;
  }
}
