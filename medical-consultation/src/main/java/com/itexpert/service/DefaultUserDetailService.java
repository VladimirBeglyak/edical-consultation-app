package com.itexpert.service;

import com.itexpert.domain.UserAccountSecurity;
import com.itexpert.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDetailService implements UserDetailsService {

  private final UserAccountRepository userAccountRepository;

  public DefaultUserDetailService(UserAccountRepository userAccountRepository) {
    this.userAccountRepository = userAccountRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userAccountRepository.findByEmail(email)
        .map(UserAccountSecurity::fromUserAccount)
        .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user by username: " + email));
  }
}
