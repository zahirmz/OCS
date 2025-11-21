package com.Ust.ocs.dao;

import java.sql.*;
import java.util.ArrayList;

import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.ScheduleBean;

public class ReporterDAO {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/cbh"; // Replace with your actual DB URL
    private static final String USER = "root";  // Replace with your MySQL username
    private static final String PASSWORD = "pass@word1";  // Replace with your MySQL password
    
    public ArrayList<DoctorBean> viewAllDoctors() {
        ArrayList<DoctorBean> doctorList = new ArrayList<>();
        String query = "SELECT * FROM doctors";  // Fetch all doctors

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DoctorBean doctor = new DoctorBean();
                doctor.setDoctorID(rs.getString("doctorID"));
                doctor.setDoctorName(rs.getString("doctorName"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctor.setYearsOfExperience(rs.getInt("yearsOfExperience"));
                // Add any other fields as needed

                doctorList.add(doctor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctorList;
    }


    // Method to report doctor's leave
    public boolean reportLeave(String leaveID, String doctorID, String leaveReason, Date leaveFrom, Date leaveTo, int status) {
        String query = "INSERT INTO leaves (LEAVEID, DOCTORID, LEAVE_FROM, LEAVE_TO, REASON, STATUS) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            // Log the query and parameters
            System.out.println("Executing query: " + query);
            System.out.println("With parameters: " + leaveID + ", " + doctorID + ", " + leaveFrom + ", " + leaveTo + ", " + leaveReason + ", " + status);

            // Set parameters for the SQL query
            ps.setString(1, leaveID);           // LEAVEID
            ps.setString(2, doctorID);          // DOCTORID
            ps.setDate(3, leaveFrom);           // LEAVE_FROM
            ps.setDate(4, leaveTo);             // LEAVE_TO
            ps.setString(5, leaveReason);      // REASON
            ps.setInt(6, status);              // STATUS

            // Execute the update query
            int rowsInserted = ps.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);

            return rowsInserted > 0;  // Return true if insertion was successful

        } catch (SQLException e) {
            e.printStackTrace();  // Print detailed error for debugging
            return false;  // Return false if an error occurs
        }
    }
    public ArrayList<DoctorBean> viewAvailableDoctors(String date) {
        ArrayList<DoctorBean> availableDoctors = new ArrayList<>();
        
        // Ensure the date format is correct (for example, YYYY-MM-DD)
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);

        String query = "SELECT * FROM doctors WHERE doctorID NOT IN (" +
                       "SELECT doctorID FROM leaves WHERE ? BETWEEN LEAVE_FROM AND LEAVE_TO)";
        
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {
            
            // Set the date parameter
            ps.setDate(1, sqlDate);

            // Execute the query
            ResultSet rs = ps.executeQuery();

            // Process the result set and add doctors to the list
            while (rs.next()) {
                DoctorBean doctor = new DoctorBean();
                doctor.setDoctorID(rs.getString("doctorID"));
                doctor.setDoctorName(rs.getString("doctorName"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctor.setYearsOfExperience(rs.getInt("yearsOfExperience"));
                availableDoctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableDoctors;  // Return the list of available doctors
    }
    
    
    public boolean removeLeave(String leaveID) {
        String query = "DELETE FROM leaves WHERE LEAVEID = ?";  // SQL query to delete the leave by LEAVEID

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            // Set the LEAVEID parameter in the query
            ps.setString(1, leaveID);

            // Execute the update query (delete operation)
            int rowsAffected = ps.executeUpdate();

            // If one or more rows were affected, the leave was removed successfully
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();  // Print the exception stack trace for debugging
            return false;  // Return false if there was an error
        }
    }

    
    
    public ArrayList<String> viewDoctorsOnLeave() {
        ArrayList<String> doctorsOnLeave = new ArrayList<>();
        
        // SQL query to join doctors and leaves tables to get the doctor details and leave details
        String query = "SELECT d.doctorID, d.doctorName, d.specialization, l.leave_From, l.leave_To, l.reason " +
                       "FROM doctors d " +
                       "JOIN leaves l ON d.doctorID = l.doctorID " +
                       "WHERE l.status = 1";  // Status = 1 means doctor is on leave

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String doctorInfo = "Doctor ID: " + rs.getString("doctorID") + "\n" +
                                    "Name: " + rs.getString("doctorName") + "\n" +
                                    "Specialization: " + rs.getString("specialization") + "\n" +
                                    "Leave From: " + rs.getDate("leave_From") + "\n" +
                                    "Leave To: " + rs.getDate("leave_To") + "\n" +
                                    "Leave Reason: " + rs.getString("reason") + "\n\n";

                // Add doctor information to the list
                doctorsOnLeave.add(doctorInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception
        }

        return doctorsOnLeave;
    }



}

