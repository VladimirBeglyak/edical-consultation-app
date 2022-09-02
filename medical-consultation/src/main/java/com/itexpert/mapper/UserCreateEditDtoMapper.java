package com.itexpert.mapper;

import com.itexpert.domain.UserAccount;
import com.itexpert.dto.UserCreateEditDto;
import org.springframework.stereotype.Component;

@Component
public class UserCreateEditDtoMapper implements Mapper<UserCreateEditDto, UserAccount> {

  @Override
  public UserAccount mapFrom(UserCreateEditDto userCreateEditDto) {
    return new UserAccount(userCreateEditDto.email(), userCreateEditDto.password());
  }

//  @Override
//  public UserAccount mapFrom(UserCreateEditDto userCreateEditDto) {
//    UserAccount userAccount = new UserAccount(userCreateEditDto.role(), userCreateEditDto.email(), userCreateEditDto.password());
//    PersonalData personalData = new PersonalData(userCreateEditDto.name(), userCreateEditDto.phone(), userCreateEditDto.image(), userCreateEditDto.birthDate());
//    if (userCreateEditDto.role() == Role.DOCTOR){
//      Doctor doctor = new Doctor(userCreateEditDto.speciality(), userCreateEditDto.document());
//      doctor.setUserAccount(userAccount);
//    }
//    if (userCreateEditDto.role() == Role.PATIENT){
//      Patient patient = new Patient();
//      patient.setUserAccount(userAccount);
//    }
//    userAccount.setPersonalData(personalData);
//    personalData.setUserAccount(userAccount);
//    return userAccount;
//  }
}
