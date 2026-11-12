package com.bcc.municipalapi.dao;

// 3.2 Insert a service request and 3.3 Retrieve all service requests
import com.bcc.municipalapi.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceRequestDAO {

    // 3.2 Insert using PreparedStatement and a parameterised query
    public void addServiceRequest(String citizenName, String requestType, String description) {
        String sql = "INSERT INTO service_requests (citizen_name, request_type, description, status, date_logged) "
           + "VALUES (?, ?, ?, ?, NOW())";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // assign the parameters (the ? placeholders)
            ps.setString(1, citizenName);
            ps.setString(2, requestType);
            ps.setString(3, description);
            ps.setString(4, "Pending");

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Service request saved successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting request: " + e.getMessage());
        }
    }

    // 3.3 Retrieve all requests using a ResultSet and display them
    public void getAllServiceRequests() {
        String sql = "SELECT request_id, citizen_name, request_type, description, status, date_logged "
                   + "FROM service_requests";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("---- All Service Requests ----");

            // loop through each record
            while (rs.next()) {
                int id = rs.getInt("request_id");
                String name = rs.getString("citizen_name");
                String type = rs.getString("request_type");
                String desc = rs.getString("description");
                String status = rs.getString("status");
                String date = rs.getString("date_logged");

                System.out.println("Request ID : " + id);
                System.out.println("Citizen    : " + name);
                System.out.println("Type       : " + type);
                System.out.println("Description: " + desc);
                System.out.println("Status     : " + status);
                System.out.println("Date logged: " + date);
                System.out.println("------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving requests: " + e.getMessage());
        }
    }
}