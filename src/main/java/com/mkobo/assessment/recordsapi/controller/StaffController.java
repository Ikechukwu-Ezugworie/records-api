package com.mkobo.assessment.recordsapi.controller;

import com.mkobo.assessment.recordsapi.pojo.StaffPojo;
import com.mkobo.assessment.recordsapi.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/create/{name}")
    public ResponseEntity<StaffPojo> createStaff(@PathVariable String name){
        StaffPojo staff = staffService.creatStaff(name);
        return new ResponseEntity<>(staff, HttpStatus.CREATED);
    }
}
