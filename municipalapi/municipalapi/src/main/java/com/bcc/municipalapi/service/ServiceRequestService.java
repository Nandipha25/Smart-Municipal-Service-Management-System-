package com.bcc.municipalapi.service;

import com.bcc.municipalapi.exception.InvalidRequestException;
import com.bcc.municipalapi.model.ServiceRequest;
import com.bcc.municipalapi.repository.ServiceRequestRepository;
import java.util.List;
import org.springframework.stereotype.Service;

// 5.3 Service class using dependency injection (updated with 6.2 validation)
@Service
public class ServiceRequestService {

    private final ServiceRequestRepository repository;

    // constructor injection: Spring gives us the repository automatically
    public ServiceRequestService(ServiceRequestRepository repository) {
        this.repository = repository;
    }

    // add a new service request
    public ServiceRequest addServiceRequest(ServiceRequest request) {
        // 6.2 reject invalid requests with a meaningful message
        if (request.getCitizenName() == null || request.getCitizenName().isBlank()) {
            throw new InvalidRequestException("Citizen name is required.");
        }
        if (request.getRequestType() == null || request.getRequestType().isBlank()) {
            throw new InvalidRequestException("Request type is required.");
        }

        // every new request starts as Pending if no status was given
        if (request.getStatus() == null || request.getStatus().isBlank()) {
            request.setStatus("Pending");
        }
        return repository.save(request);
    }

    // retrieve all service requests
    public List<ServiceRequest> getAllServiceRequests() {
        return repository.findAll();
    }
}