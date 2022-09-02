package com.itexpert.service;

import static com.itexpert.domain.QDoctorSchedule.doctorSchedule;

import com.itexpert.domain.Doctor;
import com.itexpert.domain.DoctorSchedule;
import com.itexpert.dto.ScheduleFilter;
import com.itexpert.querydsl.QPredicates;
import com.itexpert.repository.DoctorRepository;
import com.itexpert.repository.DoctorScheduleRepository;
import com.querydsl.core.types.Predicate;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DefaultDoctorScheduleService implements ScheduleService<DoctorSchedule> {

  private final DoctorScheduleRepository doctorScheduleRepository;
  private final DoctorRepository doctorRepository;

  public DefaultDoctorScheduleService(DoctorScheduleRepository doctorScheduleRepository,
      DoctorRepository doctorRepository) {
    this.doctorScheduleRepository = doctorScheduleRepository;
    this.doctorRepository = doctorRepository;
  }

  @Override
  public DoctorSchedule get(Long id) {
    return doctorScheduleRepository.findById(id)
        .orElseGet(DoctorSchedule::new);
  }

  @Override
  public DoctorSchedule create(DoctorSchedule doctorSchedule, Long id) {
    Optional<Doctor> doctor = doctorRepository.findById(id);
    if (doctor.isPresent()) {
      doctorSchedule.setDoctor(doctor.get());
      doctorSchedule.setCreateAt(LocalDateTime.now());
      return doctorScheduleRepository.save(doctorSchedule);
    }
    return null;
  }

  @Override
  public void delete(Long id) {
    doctorScheduleRepository.deleteById(id);
  }

  @Override
  public Page<DoctorSchedule> getAll(ScheduleFilter filter, Pageable pageable) {
    Predicate predicate = QPredicates.builder()
        .add(filter.email(), doctorSchedule.doctor.personalData.name::containsIgnoreCase)
        .add(filter.firstName(), doctorSchedule.doctor.personalData.name::containsIgnoreCase)
        .build();
    return doctorScheduleRepository.findAll(predicate, pageable);
  }

  @Override
  public Page<DoctorSchedule> getAll(Pageable pageable) {
    return doctorScheduleRepository.findAll(pageable);
  }

  @Override
  public DoctorSchedule update(DoctorSchedule userAccount, Long id) {
    return null;
  }
}
