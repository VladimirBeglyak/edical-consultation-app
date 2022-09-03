package com.itexpert.mapper;

import static com.itexpert.domain.Role.DOCTOR;
import static com.itexpert.domain.Role.PATIENT;

import com.itexpert.domain.DoctorSchedule;
import com.itexpert.domain.PatientSchedule;
import com.itexpert.domain.Schedule;
import com.itexpert.domain.UserAccount;
import com.itexpert.dto.ScheduleDto;
import com.itexpert.dto.UserAccountDto;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper implements Mapper<ScheduleDto, Schedule> {

  @Override
  public ScheduleDto toDto(Schedule schedule) {
    UserAccountDto userAccountDto = new UserAccountDto(schedule.getUserAccount().getId(), schedule.getUserAccount().getRole());
    if (schedule.getUserAccount().getRole() == DOCTOR) {
      DoctorSchedule doctorSchedule = (DoctorSchedule) schedule;
      return new ScheduleDto(
          doctorSchedule.getId(),
          doctorSchedule.getCreateAt(),
          userAccountDto
      );
    }
    if (schedule.getUserAccount().getRole() == PATIENT) {
      PatientSchedule patientSchedule = (PatientSchedule) schedule;
      return new ScheduleDto(
          patientSchedule.getId(),
          patientSchedule.getCreateAt(),
          patientSchedule.getDescription(),
          userAccountDto
      );
    }
    return null;
  }

  @Override
  public Schedule toEntity(ScheduleDto scheduleDto) {
    UserAccount userAccount = new UserAccount(scheduleDto.userAccount().id(), scheduleDto.userAccount().role());
    if (scheduleDto.userAccount().role() == DOCTOR) {
      return new DoctorSchedule(
          userAccount,
          LocalDateTime.now()
      );
    }
    if (scheduleDto.userAccount().role() == PATIENT) {
      return new PatientSchedule(
          userAccount,
          LocalDateTime.now(),
          scheduleDto.description()
      );
    }
    return null;
  }
}
