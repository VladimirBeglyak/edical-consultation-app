package com.itexpert.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  ADMIN,
  PATIENT,
  DOCTOR;

  private static final Map<Role, String> types = Map.of(
      ADMIN, "UserAccount",
      PATIENT, "Patient",
      DOCTOR, "Doctor"
  );

  @Override
  public String getAuthority() {
    return name();
  }

  public static List<GrantedAuthority> getAllRoles() {
    return Arrays.asList(Role.values());
  }

  public static String getTypeEntity(Role role) {
    return Optional.ofNullable(role)
        .filter(types::containsKey)
        .map(types::get)
        .orElseGet(() -> "UserAccount");
  }
}
