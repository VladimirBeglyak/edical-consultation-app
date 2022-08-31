package com.itexpert.service;

import com.itexpert.domain.Patient;
import com.itexpert.dto.UserFilter;
import com.itexpert.repository.PatientUserAccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DefaultPatientService implements UserAccountService<Patient> {

  private final PatientUserAccountRepository patientUserAccountRepository;

  public DefaultPatientService(PatientUserAccountRepository patientUserAccountRepository) {
    this.patientUserAccountRepository = patientUserAccountRepository;
  }

  @Override
  public Patient get(Long id) {
    return patientUserAccountRepository.findById(id)
        .orElseGet(Patient::new);
  }

  @Override
  public Patient create(Patient patient) {
    return patientUserAccountRepository.save(patient);
  }

  @Override
  public void delete(Long id) {
    patientUserAccountRepository.findById(id)
        .ifPresent(patientUserAccountRepository::delete);
  }

  @Override
  public Page<Patient> getAll(UserFilter filter, Pageable pageable) {
    return null;
  }

  @Override
  public Page<Patient> getAll(Pageable pageable) {
    return patientUserAccountRepository.findAll(PageRequest.of(0, 5));
  }

  @Override
  public Patient update(Patient patient, Long id) {
    return patientUserAccountRepository.findById(id)
        .map(foundedPatientUserAccount -> {
          Patient updatedPatientUserAccount = new Patient();
          updatedPatientUserAccount.setId(id);
          updatedPatientUserAccount.setFirstName(patient.getFirstName());
          updatedPatientUserAccount.setLastName(patient.getLastName());
          updatedPatientUserAccount.setRole(patient.getRole());
          return patientUserAccountRepository.save(updatedPatientUserAccount);
        })
        .orElseGet(Patient::new);
  }
}
