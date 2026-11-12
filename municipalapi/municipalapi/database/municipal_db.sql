-- ------------------------------------------------------------
-- Smart Municipal Service Management System
-- Systems Development 2A (HSYD201-1) - Summative Assessment 1
-- Database script for MySQL
-- ------------------------------------------------------------

CREATE DATABASE IF NOT EXISTS municipal_db;
USE municipal_db;

-- Table used by the JPA entity (ServiceRequest), the JDBC DAO and the JSP page
CREATE TABLE IF NOT EXISTS service_requests (
    request_id   BIGINT       NOT NULL AUTO_INCREMENT,
    citizen_name VARCHAR(255) NOT NULL,
    request_type VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    status       VARCHAR(255) NOT NULL,
    date_logged  DATETIME(6),
    PRIMARY KEY (request_id)
);

-- Sample data for testing
INSERT INTO service_requests (citizen_name, request_type, description, status, date_logged)
VALUES ('Thabo Mokoena',  'Water Leak',  'Burst pipe outside house 12',  'Pending',     NOW()),
       ('Lerato Dlamini', 'Pothole',     'Large pothole on Main Road',   'In Progress', NOW());

-- Check the data
SELECT * FROM service_requests;
