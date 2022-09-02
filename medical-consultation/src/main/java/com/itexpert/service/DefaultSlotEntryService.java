package com.itexpert.service;

import static com.itexpert.domain.Role.DOCTOR;
import static com.itexpert.domain.Role.PATIENT;

import com.itexpert.domain.SlotEntry;
import com.itexpert.domain.UserAccount;
import com.itexpert.dto.SlotEntryFilter;
import com.itexpert.querydsl.QPredicates;
import com.itexpert.repository.SlotEntryRepository;
import com.itexpert.repository.UserAccountRepository;
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
  private final UserAccountRepository userAccountRepository;

  public DefaultSlotEntryService(SlotEntryRepository slotEntryRepository, UserAccountRepository userAccountRepository) {
    this.slotEntryRepository = slotEntryRepository;
    this.userAccountRepository = userAccountRepository;
  }

  @Override
  public SlotEntry get(Long id) {
    return slotEntryRepository.findById(id)
        .orElseGet(SlotEntry::new);
  }

  @Override
  public SlotEntry create(SlotEntry slotEntry) {
    Optional<UserAccount> patient = userAccountRepository.findByIdAndRole(slotEntry.getPatientId(), PATIENT);
    Optional<UserAccount> doctor = userAccountRepository.findByIdAndRole(slotEntry.getDoctorId(), DOCTOR);
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
//        .add(filter.email(), slotEntry.doctorId::containsIgnoreCase)
//        .add(filter.firstName(), slotEntry.patientId::containsIgnoreCase)
//        .add(filter.lastName(), slotEntry.patient.email::containsIgnoreCase)
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
