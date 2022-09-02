package com.itexpert.service;

import com.itexpert.domain.Doctor;
import com.itexpert.dto.UserFilter;
import com.itexpert.repository.DoctorUserAccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DefaultDoctorService implements UserAccountService<Doctor> {

  private final DoctorUserAccountRepository doctorUserAccountRepository;

  public DefaultDoctorService(DoctorUserAccountRepository doctorUserAccountRepository) {
    this.doctorUserAccountRepository = doctorUserAccountRepository;
  }

  @Override
  public Doctor get(Long id) {
    return doctorUserAccountRepository.findById(id)
        .orElseGet(Doctor::new);
  }

  @Override
  public Doctor create(Doctor doctor) {
    return doctorUserAccountRepository.save(doctor);
  }

  @Override
  public void delete(Long id) {
    doctorUserAccountRepository.findById(id)
        .ifPresent(doctorUserAccountRepository::delete);
  }

  @Override
  public Page<Doctor> getAll(UserFilter filter, Pageable pageable) {
    return null;
  }

  @Override
  public Page<Doctor> getAll(Pageable pageable) {
    return doctorUserAccountRepository.findAll(PageRequest.of(0, 5));
  }

  @Override
  public Doctor update(Doctor userAccount, Long id) {
    return doctorUserAccountRepository.findById(id)
        .map(foundedPatientUserAccount -> {
          Doctor updatedPatientUserAccount = new Doctor();
          updatedPatientUserAccount.setId(id);
          updatedPatientUserAccount.setFirstName(userAccount.getFirstName());
          updatedPatientUserAccount.setLastName(userAccount.getLastName());
          updatedPatientUserAccount.setRole(userAccount.getRole());
          return doctorUserAccountRepository.save(updatedPatientUserAccount);
        })
        .orElseGet(Doctor::new);
  }
}
