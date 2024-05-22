package com.mkobo.assessment.recordsapi.controller;

import com.mkobo.assessment.recordsapi.pojo.PatientPojo;
import com.mkobo.assessment.recordsapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mkobo.assessment.recordsapi.util.Utility.CSV;
import static com.mkobo.assessment.recordsapi.util.Utility.PROFILE_PREFIX;

@RestController
@RequestMapping("/api/v1/patients")
@CacheConfig(cacheNames = "patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    @Cacheable(value = "patients", keyGenerator = "customKeyGenerator")
    public ResponseEntity<List<PatientPojo>> getPatientsUpToTwoYearsOld(@RequestParam String staffUUID) {
        List<PatientPojo> patient = patientService.getPatientsUpToTwoYearsOld(staffUUID);
        return new ResponseEntity<>(patient, HttpStatus.OK);

    }

    @GetMapping("/profile/download")
    public ResponseEntity<FileSystemResource> downloadPatientProfile(
            @RequestParam String name, @RequestParam int age,
            @RequestParam String staffUUID) {
        FileSystemResource response = patientService.downloadPatientProfile(name, age, staffUUID);
        HttpHeaders headers = new HttpHeaders();
        String fileName = PROFILE_PREFIX + name.replace(" ", "-").toLowerCase() + CSV;
        headers.add(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + fileName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(response);
    }

    /**
     * <p>Endpoint to delete patients who last visted in a given date range</p>
     * @param startDate
     * @param endDate
     * @param staffUUID
     * @return
     */
    @DeleteMapping("/profile")
    @CacheEvict(cacheNames = "patients", key = "", beforeInvocation = true)
    public ResponseEntity<Object> deletePatientsInRange(
            @RequestParam String startDate, @RequestParam String endDate,
            @RequestParam String staffUUID) {
        patientService.deletePatientsWithinDateRange(startDate, endDate, staffUUID);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

}
