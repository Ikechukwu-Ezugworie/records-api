package com.mkobo.assessment.recordsapi.util;

import com.mkobo.assessment.recordsapi.entity.Staff;
import com.mkobo.assessment.recordsapi.service.StaffService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SeedStaff {

    @Autowired
    StaffService staffService;

    @PostConstruct
    public void createAStaff(){
        Staff staff = new Staff();
        staff.setName("John Doe");
        staff.setUuid("bf3bcf1c-86bd-4138-8b87-0d1b4349e679");
        staff.setRegistrationDate(LocalDateTime.now());

        staffService.seedStaff(staff);
    }
}
