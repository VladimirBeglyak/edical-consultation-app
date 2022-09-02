package com.itexpert.util;

import com.itexpert.domain.UserAccountSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class SecurityContextUtil {

  private SecurityContextUtil() {
  }

  public static Long getUserAccountId() {
    return getUserAccountSecurity().getId();
  }

  public static String getUserSessionId() {
    return getUserDetails().getSessionId();
  }

  public static String getEmail() {
    return getUserAccountSecurity().getEmail();
  }

  private static UserAccountSecurity getUserAccountSecurity() {
    return (UserAccountSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  private static WebAuthenticationDetails getUserDetails() {
    return (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
  }
}
