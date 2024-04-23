package com.mkobo.assessment.recordsapi.repository;

import com.mkobo.assessment.recordsapi.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    public Optional<Staff> findByUuid(String uuid);
}
