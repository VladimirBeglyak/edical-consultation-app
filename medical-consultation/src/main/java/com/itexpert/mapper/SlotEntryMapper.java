package com.itexpert.mapper;

import com.itexpert.domain.Doctor;
import com.itexpert.domain.Patient;
import com.itexpert.domain.SlotEntry;
import com.itexpert.dto.SlotEntryDto;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class SlotEntryMapper implements Mapper<SlotEntryDto, SlotEntry> {

  @Override
  public SlotEntryDto toDto(SlotEntry slotEntry) {
    return new SlotEntryDto(
        slotEntry.getId(),
        slotEntry.getStartTime(),
        slotEntry.getStatusType(),
        slotEntry.getMeetingType(),
        slotEntry.getDoctor().getId(),
        slotEntry.getPatient().getId()
    );
  }

  @Override
  public SlotEntry toEntity(SlotEntryDto slotEntryDto) {
    Doctor doctor = new Doctor();
    doctor.setId(slotEntryDto.doctorId());
    Patient patient = new Patient();
    patient.setId(slotEntryDto.patientId());
    return new SlotEntry(
        doctor,
        patient,
        LocalDateTime.now(),
        slotEntryDto.statusType(),
        slotEntryDto.meetingType()
    );
  }
}
