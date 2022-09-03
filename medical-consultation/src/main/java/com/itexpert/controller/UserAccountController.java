package com.itexpert.controller;

import com.itexpert.dto.PageResponse;
import com.itexpert.dto.UserAccountDto;
import com.itexpert.dto.UserFilter;
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

@RequestMapping("user-account")
@RestController
public class UserAccountController {

  private final UserAccountService userAccountService;

  public UserAccountController(UserAccountService userAccountService) {
    this.userAccountService = userAccountService;
  }

  @GetMapping("{id}")
  public UserAccountDto get(@PathVariable Long id) {
    return userAccountService.get(id);
  }

  @PostMapping
  public UserAccountDto create(@RequestBody UserAccountDto userAccountDto) {
    return userAccountService.create(userAccountDto);
  }

  @PutMapping("{id}")
  public UserAccountDto update(@PathVariable Long id, @RequestBody UserAccountDto userAccountDto) {
    return userAccountService.update(userAccountDto, id);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    userAccountService.delete(id);
  }

  @GetMapping("/filter")
  public PageResponse<UserAccountDto> getAllByFilter(UserFilter filter, Pageable pageable) {
    return PageResponse.of(userAccountService.getAll(filter, pageable));
  }

  @GetMapping
  public Page<UserAccountDto> getAll(Pageable pageable) {
    return userAccountService.getAll(pageable);
  }
}
