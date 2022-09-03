package com.itexpert.repository;

import com.itexpert.domain.Schedule;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ScheduleRepository extends
    JpaRepository<Schedule, Long>,
    QuerydslPredicateExecutor<Schedule> {

  Optional<Schedule> findByCreateAtAndAndUserAccount_Id(LocalDateTime localDateTime, Long id);
}
