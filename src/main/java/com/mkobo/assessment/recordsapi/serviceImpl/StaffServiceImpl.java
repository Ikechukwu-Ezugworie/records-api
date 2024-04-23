package com.mkobo.assessment.recordsapi.serviceImpl;

import com.mkobo.assessment.recordsapi.entity.Staff;
import com.mkobo.assessment.recordsapi.pojo.StaffPojo;
import com.mkobo.assessment.recordsapi.repository.StaffRepository;
import com.mkobo.assessment.recordsapi.service.StaffService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    ModelMapper mapper = new ModelMapper();


    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public StaffPojo creatStaff(String staffName) {
        log.info("Creating a new staff");
        Optional<Staff> existingStaff = staffRepository.findByName(staffName);
        if (existingStaff.isPresent()){
            log.debug("Staff with name: {} already exists", staffName);
            throw new IllegalArgumentException("Staff with name: "+staffName+" already exists");
        }
        Staff staff = new Staff();
        staff.setName(staffName);
        staff.setUuid(UUID.randomUUID().toString());
        staff.setRegistrationDate(LocalDateTime.now());
        Staff createdStaff = staffRepository.save(staff);
        log.info("Created staff: {}", createdStaff);
        return mapper.map(createdStaff, StaffPojo.class);
    }
}
