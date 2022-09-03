package com.itexpert.domain;

import java.time.LocalDate;
import javax.persistence.Embeddable;

@Embeddable
public class PersonalData {

  private String name;
  private String phone;
  private String image;
  private LocalDate birthDate;

  public PersonalData(String name, String phone, String image, LocalDate birthDate) {
    this.name = name;
    this.phone = phone;
    this.image = image;
    this.birthDate = birthDate;
  }

  public PersonalData() {
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
