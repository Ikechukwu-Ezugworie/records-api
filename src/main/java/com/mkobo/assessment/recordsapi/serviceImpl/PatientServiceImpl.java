package com.mkobo.assessment.recordsapi.serviceImpl;

import com.mkobo.assessment.recordsapi.entity.Patient;
import com.mkobo.assessment.recordsapi.entity.Staff;
import com.mkobo.assessment.recordsapi.exception.NotFoundException;
import com.mkobo.assessment.recordsapi.pojo.PatientPojo;
import com.mkobo.assessment.recordsapi.repository.PatientRepository;
import com.mkobo.assessment.recordsapi.repository.StaffRepository;
import com.mkobo.assessment.recordsapi.service.PatientService;
import com.mkobo.assessment.recordsapi.util.Utility;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.mkobo.assessment.recordsapi.util.Utility.CSV;
import static com.mkobo.assessment.recordsapi.util.Utility.formatter;


@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    ModelMapper mapper = new ModelMapper();


    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, StaffRepository staffRepository) {
        this.patientRepository = patientRepository;
        this.staffRepository = staffRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PatientPojo> getPatientsUpToTwoYearsOld(String staffUUID) {
        log.info("Getting patients up to two years");
        Optional<Staff> staff = staffRepository.findByUuid(staffUUID);
        if (staff.isEmpty()) {
            log.info("Staff with UUID: {} does not exist", staffUUID);
            throw new NotFoundException("Staff with UUID: " + staffUUID + " does not exist");
        }
        int maxAge = 2;
        List<Patient> patients = patientRepository.findByAgeLessThanEqual(maxAge);
        return Utility.mapList(patients, PatientPojo.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileSystemResource downloadPatientProfile(String name, int age, String staffUUID) {
        log.info("Downloading patient's profile with name: {} and age: {}", name, age);
        Optional<Staff> staff = staffRepository.findByUuid(staffUUID);

        if (staff.isEmpty()) {
            log.info("Staff with UUID: {} does not exist", staffUUID);
            throw new NotFoundException("Staff with UUID: " + staffUUID + " does not exist");
        }

        Patient patient = patientRepository.findByNameAndAge(name, age)
                .orElseThrow(() -> new NotFoundException("Patient with name: " + name + " and age: " + age + " does not exist"));

        PatientPojo patientPojo = mapper.map(patient, PatientPojo.class);
        String filePath = "Profile-" + patientPojo.getName().toLowerCase(Locale.ROOT) + CSV;
        try {
            Utility.generatePatientProfileCSV(patientPojo, filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new FileSystemResource(filePath);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public boolean deletePatientsWithinDateRange(String startDate, String endDate, String staffUUID) {
        try {
            log.info("Deleting patients who last visited from: {} to {}", startDate, endDate);
            Optional<Staff> staff = staffRepository.findByUuid(staffUUID);

            if (staff.isEmpty()) {
                log.info("Staff with UUID: {} does not exist", staffUUID);
                throw new NotFoundException("Staff with UUID: " + staffUUID + " does not exist");
            }

            LocalDateTime start = LocalDateTime.parse(startDate, formatter);
            LocalDateTime end = LocalDateTime.parse(endDate, formatter);
            patientRepository.deleteByLastVisitDateBetween(start, end);

        } catch (Exception e) {
            throw new RuntimeException("Could not delete patients in the given date range becuase: ", e);
        }
        return true;
    }
}
