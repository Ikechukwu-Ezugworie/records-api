package com.mkobo.assessment.recordsapi.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class StaffPojo {
    private Long id;
    private String name;
    private String uuid;

    @JsonProperty("registration_date")
    private LocalDateTime registrationDate;

    public StaffPojo(long id, String name, String uuid, LocalDateTime registrationDate) {
        this.id = id;
        this.name = name;
        this.uuid = uuid;
        this.registrationDate = registrationDate;
    }

    public StaffPojo() {
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
