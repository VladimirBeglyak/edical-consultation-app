package com.itexpert.service;

import static com.itexpert.domain.QPatientSchedule.patientSchedule;

import com.itexpert.domain.Patient;
import com.itexpert.domain.PatientSchedule;
import com.itexpert.dto.ScheduleFilter;
import com.itexpert.querydsl.QPredicates;
import com.itexpert.repository.PatientRepository;
import com.itexpert.repository.PatientScheduleRepository;
import com.querydsl.core.types.Predicate;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DefaultPatientScheduleService implements ScheduleService<PatientSchedule> {

  private final PatientScheduleRepository patientScheduleRepository;
  private final PatientRepository patientRepository;

  public DefaultPatientScheduleService(PatientScheduleRepository patientScheduleRepository,
      PatientRepository patientRepository) {
    this.patientScheduleRepository = patientScheduleRepository;
    this.patientRepository = patientRepository;
  }

  @Override
  public PatientSchedule get(Long id) {
    return patientScheduleRepository.findById(id)
        .orElseGet(PatientSchedule::new);
  }

  @Override
  public PatientSchedule create(PatientSchedule patientSchedule, Long id) {
    Optional<Patient> patient = patientRepository.findById(id);
    if (patient.isPresent()) {
      patientSchedule.setPatient(patient.get());
      patientSchedule.setCreateAt(LocalDateTime.now());
      return patientScheduleRepository.save(patientSchedule);
    }
    return null;
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public Page<PatientSchedule> getAll(ScheduleFilter filter, Pageable pageable) {
    Predicate predicate = QPredicates.builder()
        .add(filter.email(), patientSchedule.patient.userAccount.email::containsIgnoreCase)
        .add(filter.firstName(), patientSchedule.patient.personalData.name::containsIgnoreCase)
        .build();
    return patientScheduleRepository.findAll(predicate, pageable);
  }

  @Override
  public Page<PatientSchedule> getAll(Pageable pageable) {
    return patientScheduleRepository.findAll(pageable);
  }

  @Override
  public PatientSchedule update(PatientSchedule userAccount, Long id) {
    return null;
  }
}
