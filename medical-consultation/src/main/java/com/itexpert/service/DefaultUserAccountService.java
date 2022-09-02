package com.itexpert.service;

import static com.itexpert.domain.QUserAccount.userAccount;

import com.itexpert.domain.UserAccount;
import com.itexpert.dto.UserFilter;
import com.itexpert.querydsl.QPredicates;
import com.itexpert.repository.UserAccountRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DefaultUserAccountService implements UserAccountService<UserAccount> {

  private final UserAccountRepository userAccountRepository;

  public DefaultUserAccountService(UserAccountRepository userAccountRepository) {
    this.userAccountRepository = userAccountRepository;
  }

  @Override
  public UserAccount get(Long id) {
    return userAccountRepository.findById(id)
        .orElseGet(UserAccount::new);
  }

  @Override
  public UserAccount create(UserAccount userAccount) {
    return userAccountRepository.save(userAccount);
  }

  @Override
  public void delete(Long id) {
    userAccountRepository.findById(id)
        .ifPresent(userAccountRepository::delete);
  }

  @Override
  public Page<UserAccount> getAll(UserFilter filter, Pageable pageable) {

    Predicate predicate = QPredicates.builder()
        .add(filter.email(), userAccount.email::containsIgnoreCase)
        .add(filter.firstName(), userAccount.firstName::containsIgnoreCase)
        .add(filter.lastName(), userAccount.lastName::containsIgnoreCase)
        .build();

    return userAccountRepository.findAll(predicate, pageable);
  }

  @Override
  public Page<UserAccount> getAll(Pageable pageable) {
    return userAccountRepository.findAll(pageable);
  }

  @Override
  public UserAccount update(UserAccount userAccount, Long id) {
    return userAccountRepository.findById(id)
        .map(foundedUserAccount -> {
          UserAccount updatedUserAccount = new UserAccount();
          updatedUserAccount.setId(id);
          updatedUserAccount.setFirstName(userAccount.getFirstName());
          updatedUserAccount.setLastName(userAccount.getLastName());
          updatedUserAccount.setRole(userAccount.getRole());
          return userAccountRepository.save(updatedUserAccount);
        })
        .orElseGet(UserAccount::new);
  }
}
