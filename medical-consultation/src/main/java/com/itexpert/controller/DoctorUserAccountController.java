package com.itexpert.controller;

import com.itexpert.domain.Doctor;
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

@RequestMapping("user-account/doctor")
@RestController
public class DoctorUserAccountController {

  private final UserAccountService<Doctor> userAccountService;

  public DoctorUserAccountController(UserAccountService<Doctor> userAccountService) {
    this.userAccountService = userAccountService;
  }

  @GetMapping("{id}")
  public Doctor get(@PathVariable Long id) {
    return userAccountService.get(id);
  }

  @PostMapping
  public Doctor create(@RequestBody Doctor doctor) {
    return userAccountService.create(doctor);
  }

  @PutMapping("{id}")
  public Doctor update(@PathVariable Long id, @RequestBody Doctor doctor) {
    return userAccountService.update(doctor, id);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    userAccountService.delete(id);
  }

  @GetMapping
  public Page<Doctor> getAll(Pageable pageable) {
    return userAccountService.getAll(pageable);
  }
}
