package com.mkobo.assessment.recordsapi.exception;

public class ErrorDetails {

    private String message;
    private String details;

    private int status;

    public ErrorDetails() {
    }

    public ErrorDetails(String message) {
        this.message = message;
    }

    public ErrorDetails(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public ErrorDetails(String message, String details, int status) {
        this.message = message;
        this.details = details;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
