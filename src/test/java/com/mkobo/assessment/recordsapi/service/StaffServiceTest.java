package com.mkobo.assessment.recordsapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkobo.assessment.recordsapi.entity.Staff;
import com.mkobo.assessment.recordsapi.pojo.StaffPojo;
import com.mkobo.assessment.recordsapi.repository.StaffRepository;
import com.mkobo.assessment.recordsapi.serviceImpl.StaffServiceImpl;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StaffServiceTest {


    @InjectMocks
    private StaffServiceImpl staffService;

    @Mock
    private StaffRepository staffRepository;

    @Mock
    private ModelMapper mapper = new ModelMapper();


    @Test
    public void testCreateStaff() throws Exception {
        String staffName = "Test Staff";
        Staff staff = new Staff();
        staff.setName(staffName);
        staff.setUuid(UUID.randomUUID().toString());
        staff.setRegistrationDate(LocalDateTime.now());

        when(staffRepository.findByName(staffName)).thenReturn(Optional.empty());
        when(staffRepository.save(any(Staff.class))).thenReturn(staff);

        StaffPojo createdStaff = staffService.creatStaff(staffName);

        verify(staffRepository, times(1)).findByName(staffName);
        verify(staffRepository, times(1)).save(any(Staff.class));
        assertEquals(staffName, createdStaff.getName());
    }

    @Test
    public void testCreateStaffWithExistingName() {
        String staffName = "Test Staff";
        Staff staff = new Staff();
        staff.setName(staffName);
        staff.setUuid(UUID.randomUUID().toString());
        staff.setRegistrationDate(LocalDateTime.now());

        when(staffRepository.findByName(staffName)).thenReturn(Optional.of(staff));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            staffService.creatStaff(staffName);
        });

        String expectedMessage = "Staff with name: "+staffName+" already exists";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
