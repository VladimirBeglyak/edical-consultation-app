package com.itexpert.dto;

import com.itexpert.domain.Role;
import com.itexpert.domain.Specialty;
import java.time.LocalDate;

public record UserAccountDto(
    Long id,
    Role role,
    String email,
    String password,
    String name,
    String phone,
    String image,
    String document,
    LocalDate birthDate,
    Specialty specialty
) {

  public UserAccountDto(Long id, Role role, String email, String name, String phone, String image, LocalDate birthDate, Specialty specialty) {
    this(id, role, email, null, name, phone, image, birthDate, specialty);
  }

  public UserAccountDto(Long id, Role role, String email, String name, String phone, String image, LocalDate birthDate) {
    this(id, role, email, null, name, phone, image, birthDate, null);
  }

  public UserAccountDto(Long id, Role role, String email, String name, String phone, String image, String document, LocalDate birthDate, Specialty specialty) {
    this(id, role, email, null, name, phone, image, document, birthDate, specialty);
  }

  public UserAccountDto(Long id, Role role) {
    this(id, role, null, null, null, null, null, null, null, null);
  }
}
