package com.itexpert.repository;

import com.itexpert.domain.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DoctorScheduleRepository extends
    JpaRepository<DoctorSchedule, Long>,
    QuerydslPredicateExecutor<DoctorSchedule> {

}
