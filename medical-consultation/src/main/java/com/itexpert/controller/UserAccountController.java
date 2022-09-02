package com.itexpert.controller;

import com.itexpert.dto.PageResponse;
import com.itexpert.dto.UserAccountFilter;
import com.itexpert.dto.UserReadDto;
import com.itexpert.service.UserAccountService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public UserReadDto get(@PathVariable Long id) {
    return userAccountService.get(id);
  }

//  @PostMapping
//  public UserAccount create(@RequestBody UserAccount userAccount) {
//    return userAccountService.create(userAccount);
//  }

//  @PutMapping("{id}")
//  public UserAccount update(@PathVariable Long id, @RequestBody UserAccount userAccount) {
//    return userAccountService.update(userAccount, id);
//  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    userAccountService.delete(id);
  }

  @GetMapping("/filter")
  public PageResponse<UserReadDto> getAllByFilter(UserAccountFilter filter, Pageable pageable) {
    return PageResponse.of(userAccountService.getAll(filter, pageable));
  }

  @GetMapping
  public PageResponse<UserReadDto> getAll(Pageable pageable) {
    PageResponse<UserReadDto> pageResponse = PageResponse.of(userAccountService.getAll(pageable));
    return pageResponse;
  }
}
