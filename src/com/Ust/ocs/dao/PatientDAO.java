package com.Ust.ocs.dao;

import com.Ust.ocs.bean.CredentialsBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.PatientBean;
import com.Ust.ocs.service.Patient;

import java.sql.*;
import java.util.ArrayList;

public class PatientDAO implements Patient {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/cbh"; // Update with your database URL
    private static final String USER = "root"; // Database username
    private static final String PASSWORD = "pass@word1"; // Database password
    
    public PatientDAO() {
        // You can keep this constructor if needed for any initialization logic
    }
    
    
 // Method to get user credentials from the database by userId
    public CredentialsBean getUserCredentials(String userId) {
        String query = "SELECT * FROM credentials WHERE userId = ?";
        CredentialsBean credentials = null;

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            // Set the userId parameter for the query
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            // If the user exists, populate the CredentialsBean with the data
            if (rs.next()) {
                String password = rs.getString("password");
                String userType = rs.getString("userType");
                int loginStatus = rs.getInt("loginStatus");

                credentials = new CredentialsBean(userId, password, userType, loginStatus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return credentials;  // Return the found credentials or null if no matching user
    }

    // Method to register a new patient
    public boolean registerNewPatient(CredentialsBean credentials) {
        String query = "INSERT INTO credentials (userID, password, userType, loginstatus) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            // Set values for the credentials table
            ps.setString(1, credentials.getUserId());  // Username
            ps.setString(2, credentials.getPassword());  // Password (in real-world scenarios, hash the password)
            ps.setString(3, credentials.getUserType());  // User Type (e.g., "Patient")
            ps.setInt(4, credentials.getLoginStatus());  // Login Status (default to 0 for "not logged in")

            // Execute the insert query
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;  // Return true if insertion is successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false if any error occurs
        }
    }





    
    
    public String addAilmentDetails(PatientBean patientBean) {
        String query = "INSERT INTO patient_ailments (patient_id, user_id, appointment_date, ailment_type, ailment_details, diagnosis_history) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, patientBean.getPatientID());
            ps.setString(2, patientBean.getUserID());
            ps.setDate(3, Date.valueOf(patientBean.getAppointmentDate())); // Convert string to Date
            ps.setString(4, patientBean.getAilmentType());
            ps.setString(5, patientBean.getAilmentDetails());
            ps.setString(6, patientBean.getDiagnosisHistory());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                return "SUCCESS";
            } else {
                return "FAIL";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

    
    public ArrayList<PatientBean> viewAilmentDetails(String patientID) {
        ArrayList<PatientBean> patientList = new ArrayList<>();
        String query = "SELECT * FROM patient_ailments WHERE patient_id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, patientID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PatientBean patient = new PatientBean();
                patient.setPatientID(rs.getString("patient_id"));
                patient.setUserID(rs.getString("user_id"));
                patient.setAppointmentDate(rs.getString("appointment_date"));
                patient.setAilmentType(rs.getString("ailment_type"));
                patient.setAilmentDetails(rs.getString("ailment_details"));
                patient.setDiagnosisHistory(rs.getString("diagnosis_history"));
                patientList.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patientList;
    }

    
    public boolean modifyAilmentDetails(PatientBean patientBean) {
        String query = "UPDATE patient_ailments SET ailment_type = ?, ailment_details = ?, diagnosis_history = ?, appointment_date = ? WHERE patient_id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, patientBean.getAilmentType());
            ps.setString(2, patientBean.getAilmentDetails());
            ps.setString(3, patientBean.getDiagnosisHistory());
            ps.setDate(4, Date.valueOf(patientBean.getAppointmentDate())); // Convert string to Date
            ps.setString(5, patientBean.getPatientID());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public ArrayList<DoctorBean> viewAllAvailableDoctors(String date) {
        ArrayList<DoctorBean> doctorList = new ArrayList<>();
        
        String query = "SELECT doctorID, doctorName, specialization FROM doctors";
        
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                DoctorBean doctor = new DoctorBean();
                doctor.setDoctorID(rs.getString("doctorID"));
                doctor.setDoctorName(rs.getString("doctorName"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctorList.add(doctor);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return doctorList;
    }

    @Override
    public ArrayList<DoctorBean> intimateAdmin(String date, String status) {
        // This method seems to be a placeholder, leave it as is or implement as needed
        return null;
    }
}

