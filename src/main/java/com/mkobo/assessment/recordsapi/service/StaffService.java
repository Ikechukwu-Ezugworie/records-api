package com.mkobo.assessment.recordsapi.service;

import com.mkobo.assessment.recordsapi.entity.Staff;
import com.mkobo.assessment.recordsapi.pojo.StaffPojo;
import org.springframework.stereotype.Service;

@Service
public interface StaffService {

    /**
     * <p>Create a new staff</p>
     * @param staffName
     * @return
     */
    public StaffPojo creatStaff(String staffName);


    public StaffPojo seedStaff(Staff staff);

    /**
     * <p>Update a staff by uuid</p>
     * @param uuid unique staff identifier
     * @return
     */
    public StaffPojo updateStaff(String uuid, StaffPojo staff);
}
