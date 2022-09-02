package com.itexpert.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SlotEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long doctorId;

  private Long patientId;

  private LocalDateTime startTime;

  @Enumerated(EnumType.STRING)
  private SlotEntryStatusType statusType;

  @Enumerated(EnumType.STRING)
  private SlotEntryMeetingType meetingType;

  public SlotEntry(Long doctorId, Long patientId, LocalDateTime startTime, SlotEntryStatusType statusType,
      SlotEntryMeetingType meetingType) {
    this.doctorId = doctorId;
    this.patientId = patientId;
    this.startTime = startTime;
    this.statusType = statusType;
    this.meetingType = meetingType;
  }

  public SlotEntry() {
  }

  public Long getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(Long doctorId) {
    this.doctorId = doctorId;
  }

  public Long getPatientId() {
    return patientId;
  }

  public void setPatientId(Long patientId) {
    this.patientId = patientId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public SlotEntryMeetingType getMeetingType() {
    return meetingType;
  }

  public void setMeetingType(SlotEntryMeetingType meetingType) {
    this.meetingType = meetingType;
  }
}
