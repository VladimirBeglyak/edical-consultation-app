package com.itexpert.controller;

import com.itexpert.dto.UserCreateEditDto;
import com.itexpert.dto.UserReadDto;
import com.itexpert.repository.DoctorRepository;
import com.itexpert.repository.PatientRepository;
import com.itexpert.service.UserAccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationUserAccountController {

  private final UserAccountService userAccountService;

  public RegistrationUserAccountController(UserAccountService userAccountService, DoctorRepository doctorRepository,
      PatientRepository patientRepository) {
    this.userAccountService = userAccountService;
    this.doctorRepository = doctorRepository;
    this.patientRepository = patientRepository;
  }

  @PostMapping
  public UserReadDto register(@RequestBody UserCreateEditDto userCreateEditDto) {
    return userAccountService.create(userCreateEditDto);
  }
}
