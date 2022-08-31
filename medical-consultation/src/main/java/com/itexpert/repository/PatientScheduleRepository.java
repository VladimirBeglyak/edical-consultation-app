package com.itexpert.repository;

import com.itexpert.domain.PatientSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PatientScheduleRepository extends
    JpaRepository<PatientSchedule, Long>,
    QuerydslPredicateExecutor<PatientSchedule> {

}
