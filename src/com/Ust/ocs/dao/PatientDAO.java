package com.Ust.ocs.dao;

import com.Ust.ocs.bean.AppointmentBean;
import com.Ust.ocs.bean.CredentialsBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.PatientBean;
import com.Ust.ocs.bean.ProfileBean;
import com.Ust.ocs.bean.ScheduleBean;
import com.Ust.ocs.service.Patient;

import java.sql.*;
import java.util.ArrayList;

public class PatientDAO implements Patient {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/cbh"; // Update with your database URL
    private static final String USER = "root"; // Database username
    private static final String PASSWORD = "pass@word1"; // Database password
	private static final Statement DbConnection = null;
    
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

 // In PatientDAO.java

    public boolean registerProfile(ProfileBean profile) {
        String query = "INSERT INTO profile (userID, firstName, lastName, gender, street, city) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, profile.getUserID());
            ps.setString(2, profile.getFirstName());
            ps.setString(3, profile.getLastName());
            ps.setString(4, profile.getGender());
            ps.setString(5, profile.getStreet());
            ps.setString(6, profile.getCity());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;  // Return true if insertion is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false if any error occurs
        }
    }



 // In PatientDAO.java

    public boolean registerNewPatient(CredentialsBean credentials) {
        String query = "INSERT INTO credentials (userId, password, userType, loginStatus) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, credentials.getUserId());  // User ID
            ps.setString(2, credentials.getPassword());  // Password
            ps.setString(3, credentials.getUserType());  // User Type (Patient)
            ps.setInt(4, credentials.getLoginStatus());  // Login Status (Default to 0)

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;  // Return true if insertion is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false if an error occurs
        }
    }


    
    
    public String addAilmentDetails(PatientBean patientBean) {
        String query = "INSERT INTO patient (patient_id, userId, appointment_date, ailment_type, ailment_details, diagnosis_history) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            // Print parameters to debug
            System.out.println("Adding Ailment Details: ");
            System.out.println("Patient ID: " + patientBean.getPatientID());
            System.out.println("User ID: " + patientBean.getUserID());
            System.out.println("Appointment Date: " + patientBean.getAppointmentDate());
            System.out.println("Ailment Type: " + patientBean.getAilmentType());
            System.out.println("Ailment Details: " + patientBean.getAilmentDetails());
            System.out.println("Diagnosis History: " + patientBean.getDiagnosisHistory());

            // Set parameters
            ps.setString(1, patientBean.getPatientID());
            ps.setString(2, patientBean.getUserID());
            
            // Check if the date is valid and print before setting
            try {
                Date appointmentDate = Date.valueOf(patientBean.getAppointmentDate());
                ps.setDate(3, appointmentDate);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format: " + patientBean.getAppointmentDate());
                return "Invalid date format!";
            }
            
            ps.setString(4, patientBean.getAilmentType());
            ps.setString(5, patientBean.getAilmentDetails());
            ps.setString(6, patientBean.getDiagnosisHistory());

            // Execute the update
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                return "SUCCESS";
            } else {
                return "FAIL";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "FAIL: " + e.getMessage();
        }
    }

    public ArrayList<PatientBean> viewAilmentDetails(String patientID) {
        ArrayList<PatientBean> patientList = new ArrayList<>();
        String query = "SELECT * FROM patient WHERE patient_id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, patientID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PatientBean patient = new PatientBean();
                patient.setPatientID(rs.getString("patient_id"));
                patient.setUserID(rs.getString("userId"));
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
        String query = "UPDATE patient SET ailment_type = ?, ailment_details = ?, diagnosis_history = ?, appointment_date = ? WHERE patient_id = ?";

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

    public ArrayList<String> viewSchedule(String doctorID) {
        ArrayList<String> scheduleDetails = new ArrayList<>();
        String query = "SELECT s.SCHEDULEID, s.DOCTORID, s.AVAILABLE_DAYS, s.SLOTS, d.doctorName, d.specialization, d.city " +
                       "FROM schedules s " +
                       "JOIN doctors d ON s.DOCTORID = d.doctorID " +
                       "WHERE s.DOCTORID = ?";  // Query to fetch doctor schedule details

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);  // Direct connection
             PreparedStatement ps = con.prepareStatement(query)) {

            // Set the doctorID parameter in the query
            ps.setString(1, doctorID);

            // Execute the query and store the result in a ResultSet
            ResultSet rs = ps.executeQuery();

            // Loop through the result set and fetch the required details
            while (rs.next()) {
                String scheduleInfo = "Doctor Name: " + rs.getString("doctorName") + "\n" +
                                      "Specialization: " + rs.getString("specialization") + "\n" +
                                      "City: " + rs.getString("city") + "\n" +
                                      "Schedule ID: " + rs.getString("SCHEDULEID") + "\n" +
                                      "Available Days: " + rs.getString("AVAILABLE_DAYS") + "\n" +
                                      "Time Slots: " + rs.getString("SLOTS") + "\n\n";

                // Add the schedule info to the list
                scheduleDetails.add(scheduleInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Print the exception stack trace for debugging
        }

        return scheduleDetails;
    }


    public boolean addProfileDetails(ProfileBean profile) {
        // Step 1: Declare the connection and statement objects
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            // Step 2: Establish the database connection using the correct constants
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Step 3: Create the SQL query for inserting profile details
            String sql = "INSERT INTO profile (userId, first_name, last_name, date_of_birth, gender, street, location, city, state, pincode, mobile_no, email_id, password) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Step 4: Prepare the statement and set the parameters from the ProfileBean
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, profile.getUserID());
            pstmt.setString(2, profile.getFirstName());
            pstmt.setString(3, profile.getLastName());
            pstmt.setString(4, profile.getDateOfBirth());
            pstmt.setString(5, profile.getGender());
            pstmt.setString(6, profile.getStreet());
            pstmt.setString(7, profile.getLocation());
            pstmt.setString(8, profile.getCity());
            pstmt.setString(9, profile.getState());
            pstmt.setString(10, profile.getPincode());
            pstmt.setString(11, profile.getMobileNo());
            pstmt.setString(12, profile.getEmailID());
            pstmt.setString(13, profile.getPassword()); // Password from ProfileBean

            // Step 5: Execute the query to insert data
            int rowsAffected = pstmt.executeUpdate();
            
            // Step 6: Return true if insertion is successful, false otherwise
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Step 7: Close resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
    public String generateAppointmentID() {
        String appointmentID = "A001";  // Default starting value

        // Query to get the last appointment ID
        String query = "SELECT APPOINTMENTID FROM appointments ORDER BY APPOINTMENTID DESC LIMIT 1";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                // Get the last appointment ID and extract the numeric part
                String lastAppointmentID = rs.getString("APPOINTMENTID");
                String numericPart = lastAppointmentID.substring(1);  // Remove the 'A' part
                int nextNumber = Integer.parseInt(numericPart) + 1;  // Increment the number

                // Format the number to always be 3 digits (e.g., 001, 002)
                appointmentID = "A" + String.format("%03d", nextNumber);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentID;
    }

    // Method to book appointment
    public String bookAppointment(String patientID, String doctorID, String appointmentDate, String appointmentTime) {
        // Generate a new AppointmentID
        String appointmentID = generateAppointmentID();

        // Insert the appointment into the database with status 'pending'
        String query = "INSERT INTO appointments (APPOINTMENTID, PATIENTID, DOCTORID, APPOINTMENT_DATE, APPOINTMENT_TIME, STATUS) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            // Set the values for the prepared statement
            ps.setString(1, appointmentID);
            ps.setString(2, patientID);
            ps.setString(3, doctorID);
            ps.setDate(4, Date.valueOf(appointmentDate));  // Assuming the date is in yyyy-mm-dd format
            ps.setString(5, appointmentTime);
            ps.setString(6, "pending");  // Set status as 'pending' initially

            // Execute the update
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                return "Appointment request submitted successfully! Waiting for admin approval.";
            } else {
                return "Failed to submit appointment request.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
    
 // Method to fetch all pending appointments from the database
    public ArrayList<AppointmentBean> getPendingAppointments() {
        ArrayList<AppointmentBean> pendingAppointments = new ArrayList<>();
        String query = "SELECT * FROM appointments WHERE status = 'Pending'";  // Assuming 'status' column exists

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Iterate through the result set
            while (rs.next()) {
                AppointmentBean appointment = new AppointmentBean();
                appointment.setAppointmentID(rs.getString("appointmentID"));
                appointment.setPatientID(rs.getString("patientID"));
                appointment.setDoctorID(rs.getString("doctorID"));
                appointment.setAppointmentDate(rs.getString("appointment_date"));
                appointment.setAppointmentTime(rs.getString("appointment_time"));
                pendingAppointments.add(appointment);  // Add to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingAppointments;  // Return the list of pending appointments
    }

    
// // Method to update the status of an appointment (e.g., approve or reject)
//    public boolean updateAppointmentStatus(String appointmentID, String status) {
//        String query = "UPDATE appointments SET status = ? WHERE appointmentID = ?";
//
//        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement ps = con.prepareStatement(query)) {
//
//            // Set the parameters
//            ps.setString(1, status);  // Set the status (e.g., 'Approved' or 'Rejected')
//            ps.setString(2, appointmentID);  // Set the appointmentID
//
//            // Execute the update
//            int rowsUpdated = ps.executeUpdate();
//            return rowsUpdated > 0;  // Return true if the update was successful
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;  // Return false if there was an error
//        }
//    }

    public ArrayList<AppointmentBean> viewAppointments(String patientId) {
        ArrayList<AppointmentBean> appointmentList = new ArrayList<>();

        String query = "SELECT appointmentID, doctorID, appointment_date, appointment_time, status FROM appointments WHERE patientID = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            // Set the patientId parameter in the query
            ps.setString(1, patientId);

            // Execute the query and get the result
            ResultSet rs = ps.executeQuery();

            // Process the result set and map the data to AppointmentBean objects
            while (rs.next()) {
                AppointmentBean appointment = new AppointmentBean();
                appointment.setAppointmentID(rs.getString("appointmentID"));
                appointment.setDoctorID(rs.getString("doctorID"));
                appointment.setAppointmentDate(rs.getString("appointment_date"));
                appointment.setAppointmentTime(rs.getString("appointment_time"));
                appointment.setStatus(rs.getString("status")); // Assuming the status column exists in your table

                // Add the appointment to the list
                appointmentList.add(appointment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentList; // Return the list of appointments
    }

}



