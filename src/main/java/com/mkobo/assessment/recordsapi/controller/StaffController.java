package com.mkobo.assessment.recordsapi.controller;

import com.mkobo.assessment.recordsapi.pojo.StaffPojo;
import com.mkobo.assessment.recordsapi.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/{name}")
    public ResponseEntity<StaffPojo> createStaff(@PathVariable String name){
        StaffPojo staff = staffService.creatStaff(name);
        return new ResponseEntity<>(staff, HttpStatus.CREATED);
    }


    @PutMapping("/{uuid}")
    public ResponseEntity<StaffPojo> updateStaff(@PathVariable String uuid, @RequestBody StaffPojo staffPojo){
        StaffPojo staff = staffService.updateStaff(uuid, staffPojo);
        return new ResponseEntity<>(staff, HttpStatus.CREATED);
    }
}
