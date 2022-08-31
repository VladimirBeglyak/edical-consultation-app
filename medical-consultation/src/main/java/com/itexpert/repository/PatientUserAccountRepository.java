package com.itexpert.repository;

import com.itexpert.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientUserAccountRepository extends JpaRepository<Patient, Long> {

}
