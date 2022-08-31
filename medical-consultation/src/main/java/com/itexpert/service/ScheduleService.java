package com.itexpert.service;

import com.itexpert.domain.BaseSchedule;
import com.itexpert.dto.ScheduleFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService<T extends BaseSchedule> {

  T get(Long id);

  T create(T t, Long id);

  void delete(Long id);

  Page<T> getAll(ScheduleFilter filter, Pageable pageable);

  Page<T> getAll(Pageable pageable);

  T update(T userAccount, Long id);
}
