package com.mkobo.test.recordsapi.repository;

import com.mkobo.test.recordsapi.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
