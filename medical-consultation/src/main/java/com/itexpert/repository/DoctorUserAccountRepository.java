package com.itexpert.repository;

import com.itexpert.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorUserAccountRepository extends JpaRepository<Doctor, Long> {

}
