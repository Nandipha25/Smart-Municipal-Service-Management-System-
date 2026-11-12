package com.bcc.municipalapi.dao;

// Small test class to run the JDBC code and take screenshots for the report
public class TestJDBC {
    public static void main(String[] args) {
        ServiceRequestDAO dao = new ServiceRequestDAO();
        dao.addServiceRequest("Sipho Nkosi", "Street Light", "Street light not working on Oak Avenue");
        dao.getAllServiceRequests();
    }
}