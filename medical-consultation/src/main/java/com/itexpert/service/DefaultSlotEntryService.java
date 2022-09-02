package com.itexpert.service;

import static com.itexpert.domain.QSlotEntry.slotEntry;

import com.itexpert.domain.Doctor;
import com.itexpert.domain.Patient;
import com.itexpert.domain.SlotEntry;
import com.itexpert.dto.SlotEntryFilter;
import com.itexpert.querydsl.QPredicates;
import com.itexpert.repository.DoctorUserAccountRepository;
import com.itexpert.repository.PatientUserAccountRepository;
import com.itexpert.repository.SlotEntryRepository;
import com.querydsl.core.types.Predicate;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DefaultSlotEntryService implements SlotEntryService {

  private final SlotEntryRepository slotEntryRepository;
  private final DoctorUserAccountRepository doctorUserAccountRepository;
  private final PatientUserAccountRepository patientUserAccountRepository;

  public DefaultSlotEntryService(SlotEntryRepository slotEntryRepository,
      DoctorUserAccountRepository doctorUserAccountRepository,
      PatientUserAccountRepository patientUserAccountRepository) {
    this.slotEntryRepository = slotEntryRepository;
    this.doctorUserAccountRepository = doctorUserAccountRepository;
    this.patientUserAccountRepository = patientUserAccountRepository;
  }

  @Override
  public SlotEntry get(Long id) {
    return slotEntryRepository.findById(id)
        .orElseGet(SlotEntry::new);
  }

  @Override
  public SlotEntry create(SlotEntry slotEntry) {
    Optional<Patient> patient = patientUserAccountRepository.findById(slotEntry.getPatient().getId());
    Optional<Doctor> doctor = doctorUserAccountRepository.findById(slotEntry.getDoctor().getId());
    if (patient.isPresent() && doctor.isPresent()) {
      slotEntry.setStartTime(LocalDateTime.now());
      return slotEntryRepository.save(slotEntry);
    }
    return null;
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public Page<SlotEntry> getAll(SlotEntryFilter filter, Pageable pageable) {
    Predicate predicate = QPredicates.builder()
        .add(filter.email(), slotEntry.patient.email::containsIgnoreCase)
        .add(filter.firstName(), slotEntry.patient.email::containsIgnoreCase)
        .add(filter.lastName(), slotEntry.patient.email::containsIgnoreCase)
        .build();
    return slotEntryRepository.findAll(predicate, pageable);
  }

  @Override
  public Page<SlotEntry> getAll(Pageable pageable) {
    return slotEntryRepository.findAll(pageable);
  }

  @Override
  public SlotEntry update(SlotEntry userAccount, Long id) {
    return null;
  }
}
