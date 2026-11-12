package com.bcc.municipalapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

// 5.1 ServiceRequest entity class using JPA annotations
@Entity
@Table(name = "service_requests")
public class ServiceRequest {

    // primary key, generated automatically by the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "citizen_name", nullable = false)
    private String citizenName;

    @Column(name = "request_type", nullable = false)
    private String requestType;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "date_logged")
    private LocalDateTime dateLogged;

    // default constructor (JPA needs this)
    public ServiceRequest() {
    }

    // constructor with parameters
    public ServiceRequest(String citizenName, String requestType, String description, String status) {
        this.citizenName = citizenName;
        this.requestType = requestType;
        this.description = description;
        this.status = status;
    }

    // set the date automatically when a new request is saved
    @PrePersist
    protected void onCreate() {
        this.dateLogged = LocalDateTime.now();
    }

    // getters and setters
    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateLogged() {
        return dateLogged;
    }

    public void setDateLogged(LocalDateTime dateLogged) {
        this.dateLogged = dateLogged;
    }
}