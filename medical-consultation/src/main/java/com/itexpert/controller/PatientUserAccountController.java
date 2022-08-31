package com.itexpert.controller;

import com.itexpert.domain.Patient;
import com.itexpert.service.UserAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user-account/patient")
@RestController
public class PatientUserAccountController {

  private final UserAccountService<Patient> userAccountService;

  public PatientUserAccountController(UserAccountService<Patient> userAccountService) {
    this.userAccountService = userAccountService;
  }

  @GetMapping("{id}")
  public Patient get(@PathVariable Long id) {
    return userAccountService.get(id);
  }

  @PostMapping
  public Patient create(@RequestBody Patient patient) {
    return userAccountService.create(patient);
  }

  @PutMapping("{id}")
  public Patient update(@PathVariable Long id, @RequestBody Patient patient) {
    return userAccountService.update(patient, id);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    userAccountService.delete(id);
  }

  @GetMapping
  public Page<Patient> getAll(Pageable pageable) {
    return userAccountService.getAll(pageable);
  }
}
