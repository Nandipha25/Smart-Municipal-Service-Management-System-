package com.bcc.municipalapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bcc.municipalapi.exception.InvalidRequestException;
import com.bcc.municipalapi.model.ServiceRequest;
import com.bcc.municipalapi.repository.ServiceRequestRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// 6.3 Unit tests for ServiceRequestService (repository is mocked, no database needed)
@ExtendWith(MockitoExtension.class)
class ServiceRequestServiceTest {

    @Mock
    private ServiceRequestRepository repository;

    @InjectMocks
    private ServiceRequestService service;

    @Test
    void addServiceRequest_validRequest_isSavedWithPendingStatus() {
        ServiceRequest request = new ServiceRequest("Thabo Mokoena", "Water Leak",
                "Burst pipe outside house 12", null);
        when(repository.save(any(ServiceRequest.class))).thenAnswer(inv -> inv.getArgument(0));

        ServiceRequest saved = service.addServiceRequest(request);

        assertEquals("Pending", saved.getStatus());
        assertEquals("Thabo Mokoena", saved.getCitizenName());
        verify(repository).save(request);
    }

    @Test
    void addServiceRequest_emptyCitizenName_throwsInvalidRequestException() {
        ServiceRequest request = new ServiceRequest("", "Pothole", "Large pothole", null);

        InvalidRequestException ex = assertThrows(InvalidRequestException.class,
                () -> service.addServiceRequest(request));

        assertEquals("Citizen name is required.", ex.getMessage());
        verify(repository, never()).save(any(ServiceRequest.class));
    }

    @Test
    void addServiceRequest_missingRequestType_throwsInvalidRequestException() {
        ServiceRequest request = new ServiceRequest("Lerato Dlamini", null, "Street light out", null);

        InvalidRequestException ex = assertThrows(InvalidRequestException.class,
                () -> service.addServiceRequest(request));

        assertEquals("Request type is required.", ex.getMessage());
        verify(repository, never()).save(any(ServiceRequest.class));
    }

    @Test
    void getAllServiceRequests_returnsAllRequestsFromRepository() {
        List<ServiceRequest> requests = List.of(
                new ServiceRequest("Thabo Mokoena", "Water Leak", "Burst pipe", "Pending"),
                new ServiceRequest("Lerato Dlamini", "Pothole", "Main Road", "Pending"));
        when(repository.findAll()).thenReturn(requests);

        List<ServiceRequest> result = service.getAllServiceRequests();

        assertEquals(2, result.size());
        assertEquals("Lerato Dlamini", result.get(1).getCitizenName());
    }
}