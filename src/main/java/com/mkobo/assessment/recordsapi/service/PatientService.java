package com.mkobo.assessment.recordsapi.service;

import com.mkobo.assessment.recordsapi.pojo.PatientPojo;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PatientService {

    /**
     * <p>Get a list of all patients up to 2 years old</p>
     * @param staffUUID UUID of staff creating this request
     * @return
     */
    List<PatientPojo> getPatientsUpToTwoYearsOld(String staffUUID);

    /**
     * <p>Download a patient's profile</p>
     * @param name patient's name
     * @param age age of the patient
     * @param staffUUID UUID of staff creating this request
     * @return
     */
    FileSystemResource downloadPatientProfile(String name, int age, String staffUUID);

    /**
     * <p>Delete patients who last visited in a specified date range</p>
     * @param startDate beginning of the date range
     * @param endDate ending of the dateRange
     * @param staffUUID UUID of staff creating this request
     * @return
     */
    boolean deletePatientsWithinDateRange(String startDate, String endDate, String staffUUID);

}
