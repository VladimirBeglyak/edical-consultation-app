package com.itexpert.controller;

import com.itexpert.domain.UserAccount;
import com.itexpert.dto.PageResponse;
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

  private final UserAccountService<UserAccount> userAccountService;

  public UserAccountController(UserAccountService<UserAccount> userAccountService) {
    this.userAccountService = userAccountService;
  }

  @GetMapping("{id}")
  public UserAccount get(@PathVariable Long id) {
    return userAccountService.get(id);
  }

  @PostMapping
  public UserAccount create(@RequestBody UserAccount userAccount) {
    return userAccountService.create(userAccount);
  }

  @PutMapping("{id}")
  public UserAccount update(@PathVariable Long id, @RequestBody UserAccount userAccount) {
    return userAccountService.update(userAccount, id);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    userAccountService.delete(id);
  }

  @GetMapping("/filter")
  public PageResponse<UserAccount> getAllByFilter(UserFilter filter, Pageable pageable) {
    Page<UserAccount> page = userAccountService.getAll(filter, pageable);
    return PageResponse.of(page);
  }

  @GetMapping
  public Page<UserAccount> getAll(Pageable pageable) {
    return userAccountService.getAll(pageable);
  }
}
