package com.itexpert.domain;

import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  ADMIN,
  PATIENT,
  DOCTOR;

  @Override
  public String getAuthority() {
    return name();
  }

  public static List<GrantedAuthority> getAllRoles() {
    return Arrays.asList(Role.values());
  }
}
