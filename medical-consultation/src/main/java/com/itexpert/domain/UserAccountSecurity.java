package com.itexpert.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAccountSecurity implements UserDetails {

  private Long id;
  private String email;
  private String password;
  private String username;
  private List<GrantedAuthority> grantedAuthorities;

  public UserAccountSecurity(Long id, String email, String password, String username,
      List<GrantedAuthority> grantedAuthorities) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.username = username;
    this.grantedAuthorities = grantedAuthorities;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public List<GrantedAuthority> getGrantedAuthorities() {
    return grantedAuthorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public static UserDetails fromUserAccount(UserAccount userAccount) {
    return new UserAccountSecurity(
        userAccount.getId(),
        userAccount.getEmail(),
        userAccount.getPassword(),
        userAccount.getEmail(),
        Collections.singletonList(userAccount.getRole())
    );
  }

  @Override
  public String toString() {
    return "UserAccountSecurity{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", username='" + username + '\'' +
        ", grantedAuthorities=" + grantedAuthorities +
        '}';
  }
}
