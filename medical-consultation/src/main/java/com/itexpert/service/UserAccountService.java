package com.itexpert.service;

import com.itexpert.domain.UserAccount;
import com.itexpert.dto.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserAccountService<T extends UserAccount> {

  T get(Long id);

  T create(T t);

  void delete(Long id);

  Page<T> getAll(UserFilter filter, Pageable pageable);

  Page<T> getAll(Pageable pageable);

  T update(T userAccount, Long id);
}
