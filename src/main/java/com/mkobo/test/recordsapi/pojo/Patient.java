package com.mkobo.test.recordsapi.pojo;

import java.time.LocalDateTime;

public class Patient {
    private Long id;
    private String name;
    private int age;
    private LocalDateTime lastVisitDate;

    public Patient(long id, String name, int age, LocalDateTime lastVisitDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.lastVisitDate = lastVisitDate;
    }

    public Patient() {
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
