package com.itexpert.controller;

import com.itexpert.dto.UserAccountDto;
import com.itexpert.service.UserAccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegistrationUserAccountController {

  private final UserAccountService userAccountService;

  public RegistrationUserAccountController(UserAccountService userAccountService) {
    this.userAccountService = userAccountService;
  }

  @PostMapping
  public UserAccountDto signUp(@RequestBody UserAccountDto userAccountDto) {
    return userAccountService.create(userAccountDto);
  }
}
