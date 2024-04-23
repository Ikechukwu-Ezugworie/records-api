package com.mkobo.assessment.recordsapi.controller;

import com.mkobo.assessment.recordsapi.pojo.PatientPojo;
import com.mkobo.assessment.recordsapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.mkobo.assessment.recordsapi.util.Utility.CSV;
import static com.mkobo.assessment.recordsapi.util.Utility.PROFILE_PREFIX;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
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
        String fileName = PROFILE_PREFIX +name.replace(" ", "-").toLowerCase() + CSV;
        headers.add(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename="+fileName );

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(response);
    }

}
