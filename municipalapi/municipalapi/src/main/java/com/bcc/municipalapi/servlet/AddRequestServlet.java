package com.bcc.municipalapi.servlet;

import com.bcc.municipalapi.dao.ServiceRequestDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// 4.1 Servlet to capture municipal service requests
@WebServlet("/submitRequest")
public class AddRequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // retrieve the form parameters
        String citizenName = request.getParameter("citizenName");
        String requestType = request.getParameter("requestType");
        String description = request.getParameter("description");

        // simple check so empty requests are not saved
        if (citizenName == null || citizenName.isBlank()
                || requestType == null || requestType.isBlank()
                || description == null || description.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/index.html");
            return;
        }

        // save the request using the DAO from Question 3
        ServiceRequestDAO dao = new ServiceRequestDAO();
        dao.addServiceRequest(citizenName.trim(), requestType.trim(), description.trim());

        // redirect after submission to the page that lists the requests
        response.sendRedirect(request.getContextPath() + "/viewRequests.jsp");
    }
}