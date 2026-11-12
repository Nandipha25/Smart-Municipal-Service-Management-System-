package com.bcc.municipalapi.controller;

import com.bcc.municipalapi.model.ServiceRequest;
import com.bcc.municipalapi.service.ServiceRequestService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 5.4 REST Controller with POST and GET endpoints
@RestController
@RequestMapping("/api/requests")
public class ServiceRequestController {

    private final ServiceRequestService service;

    // the service is injected by Spring
    public ServiceRequestController(ServiceRequestService service) {
        this.service = service;
    }

    // POST /api/requests : add a new service request
    @PostMapping
    public ResponseEntity<ServiceRequest> addRequest(@RequestBody ServiceRequest request) {
        ServiceRequest saved = service.addServiceRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET /api/requests : get all service requests
    @GetMapping
    public List<ServiceRequest> getAllRequests() {
        return service.getAllServiceRequests();
    }
}