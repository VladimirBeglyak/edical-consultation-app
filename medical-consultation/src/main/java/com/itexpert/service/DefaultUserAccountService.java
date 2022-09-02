package com.itexpert.service;

import static com.itexpert.domain.QUserAccount.userAccount;

import com.itexpert.domain.Role;
import com.itexpert.domain.UserAccount;
import com.itexpert.dto.UserAccountFilter;
import com.itexpert.dto.UserCreateEditDto;
import com.itexpert.dto.UserReadDto;
import com.itexpert.mapper.UserCreateEditDtoMapper;
import com.itexpert.mapper.UserReadDtoMapper;
import com.itexpert.querydsl.QPredicates;
import com.itexpert.repository.DoctorRepository;
import com.itexpert.repository.PatientRepository;
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

@Transactional(readOnly = true)
@Service
public class DefaultUserAccountService implements UserAccountService {

  private static Logger LOG = LogManager.getLogger();

  private final UserAccountRepository userAccountRepository;
  private final DoctorRepository doctorRepository;
  private final PatientRepository patientRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserReadDtoMapper userReadDtoMapper;
  private final UserCreateEditDtoMapper userCreateEditDtoMapper;

  public DefaultUserAccountService(UserAccountRepository userAccountRepository, DoctorRepository doctorRepository,
      PatientRepository patientRepository, PasswordEncoder passwordEncoder,
      UserReadDtoMapper userReadDtoMapper, UserCreateEditDtoMapper userCreateEditDtoMapper) {
    this.userAccountRepository = userAccountRepository;
    this.doctorRepository = doctorRepository;
    this.patientRepository = patientRepository;
    this.passwordEncoder = passwordEncoder;
    this.userReadDtoMapper = userReadDtoMapper;
    this.userCreateEditDtoMapper = userCreateEditDtoMapper;
  }

  @Override
  public UserReadDto get(Long id) {
    return userAccountRepository.findById(id)
        .map(userReadDtoMapper::mapFrom)
        .orElseThrow(() -> new IllegalStateException(String.format("UserAccount with id %s doesn't exist", id)));
  }

  @Override
  @Transactional
  public UserReadDto create(UserCreateEditDto userCreateEditDto) {
    Optional<UserAccount> existedUserAccount = userAccountRepository.findByEmail(userCreateEditDto.email());
    if (!existedUserAccount.isEmpty()) {
      if (existedUserAccount.get().getRole() == Role.ADMIN){

      }
      UserAccount userAccount = userCreateEditDtoMapper.mapFrom(userCreateEditDto);
      setPasswordEncode(userAccount);
      return userReadDtoMapper.mapFrom(userAccountRepository.save(userAccount));
    } else {
      throw new IllegalStateException(String.format("UserAccount with email %s already exists", userCreateEditDto.email()));
    }
  }

  @Override
  @Transactional
  public void delete(Long id) {
    userAccountRepository.findById(id)
        .ifPresent(userAccountRepository::delete);
  }

  @Override
  @PreAuthorize("hasAnyAuthority('ADMIN')")
  public Page<UserReadDto> getAll(UserAccountFilter filter, Pageable pageable) {

    Predicate predicate = QPredicates.builder()
        .add(filter.email(), userAccount.email::containsIgnoreCase)
        .add(filter.firstName(), userAccount.email::containsIgnoreCase)
        .build();

    return userAccountRepository.findAll(predicate, pageable)
        .map(userReadDtoMapper::mapFrom);
  }

  @Override
  @PreAuthorize("hasAuthority('ADMIN')")
  public Page<UserReadDto> getAll(Pageable pageable) {
    LOG.info("user-account-email: {}", SecurityContextUtil.getEmail());
    LOG.info("userSessionId: {}", SecurityContextUtil.getUserSessionId());
    LOG.info("user-account-id: {}", SecurityContextUtil.getUserAccountId());
    return userAccountRepository.findAll(pageable)
        .map(userReadDtoMapper::mapFrom);
  }

  @Override
  @Transactional
  public UserReadDto update(UserCreateEditDto userCreateEditDto, Long id) {
    return

        userAccountRepository.findById(id)
            .map(foundedUserAccount -> {
              UserAccount userAccount = userCreateEditDtoMapper.mapFrom(userCreateEditDto);
              return userAccountRepository.saveAndFlush(userAccount);
            })
            .map(userReadDtoMapper::mapFrom)
            .orElseThrow(() -> new IllegalStateException(String.format("UserAccount with id %s doesn't exist", id)));
  }

  public void setPasswordEncode(UserAccount userAccount) {
    String encodePassword = passwordEncoder.encode(userAccount.getPassword());
    userAccount.setPassword(encodePassword);
  }
}

