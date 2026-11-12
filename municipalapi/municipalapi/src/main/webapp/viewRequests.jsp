<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 4.2 JSP directives and imports --%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.bcc.municipalapi.util.DBConnection" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Service Requests</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f4f4; padding: 30px; }
        table { border-collapse: collapse; width: 100%; background: #fff; }
        th, td { border: 1px solid #ccc; padding: 8px 10px; text-align: left; }
        th { background: #7b1e3a; color: #fff; }
        a { display: inline-block; margin-top: 16px; }
    </style>
</head>
<body>
    <h1>All Service Requests</h1>

    <table>
        <tr>
            <th>ID</th>
            <th>Citizen</th>
            <th>Type</th>
            <th>Description</th>
            <th>Status</th>
            <th>Date logged</th>
        </tr>
<%
    // establish the database connection (DBConnection from Question 3)
    String sql = "SELECT request_id, citizen_name, request_type, description, status, date_logged "
               + "FROM service_requests ORDER BY request_id";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        // iterate through the ResultSet and display each record dynamically
        while (rs.next()) {
            String date = rs.getString("date_logged");
%>
        <tr>
            <td><%= rs.getInt("request_id") %></td>
            <td><%= rs.getString("citizen_name") %></td>
            <td><%= rs.getString("request_type") %></td>
            <td><%= rs.getString("description") %></td>
            <td><%= rs.getString("status") %></td>
            <td><%= date == null ? "-" : date %></td>
        </tr>
<%
        }
    } catch (SQLException e) {
%>
        <tr><td colspan="6">Could not load requests: <%= e.getMessage() %></td></tr>
<%
    }
%>
    </table>

    <a href="index.html">Log a new request</a>
</body>
</html>
