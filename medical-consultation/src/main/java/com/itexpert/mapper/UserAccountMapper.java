package com.itexpert.mapper;

import static com.itexpert.domain.Role.ADMIN;
import static com.itexpert.domain.Role.DOCTOR;
import static com.itexpert.domain.Role.PATIENT;

import com.itexpert.domain.Doctor;
import com.itexpert.domain.Patient;
import com.itexpert.domain.PersonalData;
import com.itexpert.domain.UserAccount;
import com.itexpert.dto.UserAccountDto;
import org.springframework.stereotype.Component;

@Component
public class UserAccountMapper implements Mapper<UserAccountDto, UserAccount> {

  @Override
  public UserAccountDto toDto(UserAccount userAccount) {
    if (userAccount.getRole() == DOCTOR) {
      Doctor doctor = (Doctor) userAccount;
      return new UserAccountDto(
          doctor.getId(),
          doctor.getRole(),
          doctor.getEmail(),
          doctor.getPersonalData().getName(),
          doctor.getPersonalData().getPhone(),
          doctor.getPersonalData().getImage(),
          doctor.getPersonalData().getBirthDate(),
          doctor.getSpecialty()
      );
    }
    if (userAccount.getRole() == PATIENT) {
      Patient patient = (Patient) userAccount;
      return new UserAccountDto(
          patient.getId(),
          patient.getRole(),
          patient.getEmail(),
          patient.getPersonalData().getName(),
          patient.getPersonalData().getPhone(),
          patient.getPersonalData().getImage(),
          patient.getPersonalData().getBirthDate()
      );
    }
    if (userAccount.getRole() == ADMIN) {
      return new UserAccountDto(
          userAccount.getId(),
          userAccount.getRole(),
          userAccount.getEmail(),
          userAccount.getPersonalData().getName(),
          userAccount.getPersonalData().getPhone(),
          userAccount.getPersonalData().getImage(),
          userAccount.getPersonalData().getBirthDate()
      );
    }
    return null;
  }

  @Override
  public UserAccount toEntity(UserAccountDto userAccountDto) {
    PersonalData personalData = new PersonalData(
        userAccountDto.name(),
        userAccountDto.phone(),
        userAccountDto.image(),
        userAccountDto.birthDate()
    );
    if (userAccountDto.role() == DOCTOR) {
      Doctor doctor = new Doctor(
          userAccountDto.role(),
          userAccountDto.email(),
          userAccountDto.password(),
          userAccountDto.specialty()
      );
      doctor.setPersonalData(personalData);
      return doctor;
    }
    if (userAccountDto.role() == PATIENT) {
      Patient patient = new Patient(
          userAccountDto.role(),
          userAccountDto.email(),
          userAccountDto.password()
      );
      patient.setPersonalData(personalData);
      return patient;
    }
    if (userAccountDto.role() == ADMIN) {
      UserAccount userAccount = new UserAccount(
          userAccountDto.role(),
          userAccountDto.email(),
          userAccountDto.password()
      );
      userAccount.setPersonalData(personalData);
      return userAccount;
    }
    return null;
  }
}
