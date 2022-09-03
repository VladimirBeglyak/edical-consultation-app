package com.itexpert.service;

import static com.itexpert.domain.QSlotEntry.slotEntry;

import com.itexpert.domain.Doctor;
import com.itexpert.domain.Patient;
import com.itexpert.domain.SlotEntry;
import com.itexpert.dto.SlotEntryDto;
import com.itexpert.dto.SlotEntryFilter;
import com.itexpert.mapper.SlotEntryMapper;
import com.itexpert.querydsl.QPredicates;
import com.itexpert.repository.DoctorRepository;
import com.itexpert.repository.PatientRepository;
import com.itexpert.repository.SlotEntryRepository;
import com.querydsl.core.types.Predicate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DefaultSlotEntryService implements SlotEntryService {

  private final SlotEntryRepository slotEntryRepository;
  private final DoctorRepository doctorRepository;
  private final PatientRepository patientRepository;
  private final SlotEntryMapper slotEntryMapper;

  public DefaultSlotEntryService(SlotEntryRepository slotEntryRepository, DoctorRepository doctorRepository,
      PatientRepository patientRepository, SlotEntryMapper slotEntryMapper) {
    this.slotEntryRepository = slotEntryRepository;
    this.doctorRepository = doctorRepository;
    this.patientRepository = patientRepository;
    this.slotEntryMapper = slotEntryMapper;
  }

  @Override
  public SlotEntryDto get(Long id) {
    return slotEntryRepository.findById(id)
        .map(slotEntryMapper::toDto)
        .orElseThrow(() -> new IllegalStateException(String.format("SlotEntry with id %s doesn't exist", id)));
  }

  @Override
  public SlotEntryDto create(SlotEntryDto slotEntryDto) {
    Optional<Patient> patient = patientRepository.findById(slotEntryDto.patientId());
    Optional<Doctor> doctor = doctorRepository.findById(slotEntryDto.doctorId());
    if (patient.isPresent() && doctor.isPresent()) {
      SlotEntry slotEntry = slotEntryMapper.toEntity(slotEntryDto);
      return slotEntryMapper.toDto(slotEntryRepository.save(slotEntry));
    } else {
      throw new IllegalStateException(
          String.format("SlotEntry already exists or patient with id %s and doctor with id %s doesn't exist",
              slotEntryDto.patientId(), slotEntryDto.doctorId()));
    }
  }

  @Override
  public void delete(Long id) {
    slotEntryRepository.findById(id)
        .ifPresent(slotEntryRepository::delete);
  }

  @Override
  public Page<SlotEntryDto> getAll(SlotEntryFilter filter, Pageable pageable) {
    Predicate predicate = QPredicates.builder()
        .add(filter.email(), slotEntry.patient.email::containsIgnoreCase)
        .add(filter.firstName(), slotEntry.doctor.email::containsIgnoreCase)
        .build();
    return slotEntryRepository.findAll(predicate, pageable)
        .map(slotEntryMapper::toDto);
  }

  @Override
  public Page<SlotEntryDto> getAll(Pageable pageable) {
    return slotEntryRepository.findAll(pageable)
        .map(slotEntryMapper::toDto);
  }

  @Override
  public SlotEntryDto update(SlotEntryDto slotEntryDto, Long id) {
    return slotEntryRepository.findById(id)
        .map(foundedSlotEntry -> {
          SlotEntry slotEntry = slotEntryMapper.toEntity(slotEntryDto);
          return slotEntryRepository.saveAndFlush(slotEntry);
        })
        .map(slotEntryMapper::toDto)
        .orElseThrow(() -> new IllegalStateException(String.format("SlotEntryMapper with id %s doesn't exist", id)));
  }
}
