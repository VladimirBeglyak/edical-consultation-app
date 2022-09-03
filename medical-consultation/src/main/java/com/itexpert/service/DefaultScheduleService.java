package com.itexpert.service;

import static com.itexpert.domain.QSchedule.schedule;

import com.itexpert.domain.Schedule;
import com.itexpert.dto.ScheduleDto;
import com.itexpert.dto.ScheduleFilter;
import com.itexpert.mapper.ScheduleMapper;
import com.itexpert.querydsl.QPredicates;
import com.itexpert.repository.ScheduleRepository;
import com.itexpert.util.SecurityContextUtil;
import com.querydsl.core.types.Predicate;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DefaultScheduleService implements ScheduleService {

  private static Logger LOG = LogManager.getLogger();

  private final ScheduleRepository scheduleRepository;
  private final ScheduleMapper scheduleMapper;

  public DefaultScheduleService(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
    this.scheduleRepository = scheduleRepository;
    this.scheduleMapper = scheduleMapper;
  }

  @Override
  public ScheduleDto get(Long id) {
    return scheduleRepository.findById(id)
        .map(scheduleMapper::toDto)
        .orElseThrow(() -> new IllegalStateException(String.format("Schedule with id %s doesn't exist", id)));
  }

  @Override
  @Transactional
  public ScheduleDto create(ScheduleDto scheduleDto) {
//    Optional<Schedule> existedSchedule = scheduleRepository.findByCreateAtAndAndUserAccount_Id(scheduleDto.createAt(),
//        scheduleDto.userAccount().id());
//    if (existedSchedule.isEmpty()) {
      Schedule schedule = scheduleMapper.toEntity(scheduleDto);
      return scheduleMapper.toDto(scheduleRepository.save(schedule));
//    } else {
//      throw new IllegalStateException("Schedule already exists");
//    }
  }

  @Override
  @Transactional
  public void delete(Long id) {
    scheduleRepository.findById(id)
        .ifPresent(scheduleRepository::delete);
  }

  @Override
  public Page<ScheduleDto> getAll(ScheduleFilter filter, Pageable pageable) {

    Predicate predicate = QPredicates.builder()
        .add(filter.name(), schedule.userAccount.personalData.name::containsIgnoreCase)
        .build();

    return scheduleRepository.findAll(predicate, pageable)
        .map(scheduleMapper::toDto);
  }

  @Override
  @PreAuthorize("hasAuthority('ADMIN')")
  public Page<ScheduleDto> getAll(Pageable pageable) {
    LOG.info("user-account-email: {}", SecurityContextUtil.getEmail());
    LOG.info("userSessionId: {}", SecurityContextUtil.getUserSessionId());
    LOG.info("user-account-id: {}", SecurityContextUtil.getUserAccountId());
    return scheduleRepository.findAll(pageable)
        .map(scheduleMapper::toDto);
  }

  @Override
  @Transactional
  public ScheduleDto update(ScheduleDto scheduleDto, Long id) {
    return scheduleRepository.findById(id)
        .map(foundedSchedule -> {
          Schedule schedule = scheduleMapper.toEntity(scheduleDto);
          return scheduleRepository.saveAndFlush(schedule);
        })
        .map(scheduleMapper::toDto)
        .orElseThrow(() -> new IllegalStateException(String.format("Schedule with id %s doesn't exist", id)));
  }
}
