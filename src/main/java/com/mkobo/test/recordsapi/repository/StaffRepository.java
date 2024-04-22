package com.mkobo.test.recordsapi.repository;

import com.mkobo.test.recordsapi.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
