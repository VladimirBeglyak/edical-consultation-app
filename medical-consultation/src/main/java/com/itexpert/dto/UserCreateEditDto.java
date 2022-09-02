package com.itexpert.dto;

import com.itexpert.domain.Role;
import com.itexpert.domain.Specialty;

public record UserCreateEditDto(
    String name,
    String email,
    String password,
    Role role,
    String phone,
    Specialty speciality,
    String document,
    String image,
    String birthDate
) {

}
