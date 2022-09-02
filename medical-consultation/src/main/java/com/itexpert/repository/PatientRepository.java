package com.itexpert.repository;

import com.itexpert.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PatientRepository extends
    JpaRepository<Patient, Long>,
    QuerydslPredicateExecutor<Patient> {

}
