package com.itexpert;

import com.itexpert.domain.Doctor;
import com.itexpert.domain.Role;
import com.itexpert.domain.UserAccount;
import com.itexpert.dto.UserCreateEditDto;

public class FactoryUserAccount {

  public static UserAccount createEntity(UserCreateEditDto userCreateEditDto,Role role){
    switch (role){
      case ADMIN -> new UserAccount(userCreateEditDto.email(), userCreateEditDto.password());
      case DOCTOR -> new Doctor();
    }
    return null;
  }

}
