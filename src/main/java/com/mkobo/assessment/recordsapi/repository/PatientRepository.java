package com.mkobo.assessment.recordsapi.repository;

import com.mkobo.assessment.recordsapi.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    public List<Patient> findByAgeLessThanEqual(int maxAge);

    public Optional<Patient> findByNameAndAge(String name, int age);
}
