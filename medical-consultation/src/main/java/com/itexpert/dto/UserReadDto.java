package com.itexpert.dto;

import com.itexpert.domain.Role;
import com.itexpert.domain.Specialty;
import java.time.LocalDate;

public record UserReadDto(
    Long id,
    String name,
    String email,
    Role role,
    String phone,
    Specialty speciality,
    String document,
    String image,
    LocalDate birthDate
) {

}
