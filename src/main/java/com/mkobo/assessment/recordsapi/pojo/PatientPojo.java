package com.mkobo.assessment.recordsapi.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PatientPojo {
    private Long id;
    private String name;
    private int age;

    @JsonProperty("last_visit_date")
    private LocalDateTime lastVisitDate;

    public PatientPojo(long id, String name, int age, LocalDateTime lastVisitDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.lastVisitDate = lastVisitDate;
    }

    public PatientPojo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDateTime lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }
}
