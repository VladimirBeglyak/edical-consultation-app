package com.itexpert.service;

import static com.itexpert.domain.QUserAccount.userAccount;

import com.itexpert.domain.UserAccount;
import com.itexpert.dto.UserAccountDto;
import com.itexpert.dto.UserFilter;
import com.itexpert.mapper.UserAccountMapper;
import com.itexpert.querydsl.QPredicates;
import com.itexpert.repository.UserAccountRepository;
import com.itexpert.util.SecurityContextUtil;
import com.querydsl.core.types.Predicate;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultUserAccountService implements UserAccountService {

  private static Logger LOG = LogManager.getLogger();

  private final UserAccountRepository userAccountRepository;
  private final UserAccountMapper userAccountMapper;
  private final PasswordEncoder passwordEncoder;

  public DefaultUserAccountService(UserAccountRepository userAccountRepository, UserAccountMapper userAccountMapper,
      PasswordEncoder passwordEncoder) {
    this.userAccountRepository = userAccountRepository;
    this.userAccountMapper = userAccountMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserAccountDto get(Long id) {
    return userAccountRepository.findById(id)
        .map(userAccountMapper::toDto)
        .orElseThrow(() -> new IllegalStateException(String.format("UserAccount with id %s doesn't exist", id)));
  }

  @Override
  @Transactional
  public UserAccountDto create(UserAccountDto userAccountDto) {
    Optional<UserAccount> existedUserAccount = userAccountRepository.findByEmail(userAccountDto.email());
    if (existedUserAccount.isEmpty()) {
      UserAccount userAccount = userAccountMapper.toEntity(userAccountDto);
      setPasswordEncode(userAccount);
      return userAccountMapper.toDto(userAccountRepository.save(userAccount));
    } else {
      throw new IllegalStateException(
          String.format("UserAccount with email %s already exists", userAccountDto.email()));
    }
  }

  @Override
  @Transactional
  public void delete(Long id) {
    userAccountRepository.findById(id)
        .ifPresent(userAccountRepository::delete);
  }

  @Override
  public Page<UserAccountDto> getAll(UserFilter filter, Pageable pageable) {

    Predicate predicate = QPredicates.builder()
        .add(filter.email(), userAccount.email::containsIgnoreCase)
        .add(filter.name(), userAccount.personalData.name::containsIgnoreCase)
        .build();

    return userAccountRepository.findAll(predicate, pageable)
        .map(userAccountMapper::toDto);
  }

  @Override
  @PreAuthorize("hasAuthority('ADMIN')")
  public Page<UserAccountDto> getAll(Pageable pageable) {
    LOG.info("user-account-email: {}", SecurityContextUtil.getEmail());
    LOG.info("userSessionId: {}", SecurityContextUtil.getUserSessionId());
    LOG.info("user-account-id: {}", SecurityContextUtil.getUserAccountId());
    return userAccountRepository.findAll(pageable)
        .map(userAccountMapper::toDto);
  }

  @Override
  @Transactional
  public UserAccountDto update(UserAccountDto userAccountDto, Long id) {
    return userAccountRepository.findById(id)
        .map(foundedUserAccount -> {
          UserAccount userAccount = userAccountMapper.toEntity(userAccountDto);
          return userAccountRepository.saveAndFlush(userAccount);
        })
        .map(userAccountMapper::toDto)
        .orElseThrow(() -> new IllegalStateException(String.format("UserAccount with id %s doesn't exist", id)));
  }

  public void setPasswordEncode(UserAccount userAccount) {
    String encodePassword = passwordEncoder.encode(userAccount.getPassword());
    userAccount.setPassword(encodePassword);
  }
}
