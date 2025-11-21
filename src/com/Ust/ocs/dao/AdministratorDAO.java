package com.Ust.ocs.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

import com.Ust.ocs.bean.AppointmentBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.PatientBean;
import com.Ust.ocs.bean.ScheduleBean;
import com.Ust.ocs.service.Administrator;
import com.Ust.ocs.util.DBUtil;

public class AdministratorDAO implements Administrator {

	
	// Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/cbh";  // Replace with your actual database URL
    private static final String USER = "root";  // Replace with your actual database username
    private static final String PASSWORD = "pass@word1";  // Replace with your actual database password

    // Other methods and DAO logic go here
    @Override
    public String addDoctor(DoctorBean doctorBean) {
        String sql = "INSERT INTO doctors VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, doctorBean.getDoctorID());
            ps.setString(2, doctorBean.getDoctorName());
            ps.setString(3, doctorBean.getDateOfBirth());
            ps.setString(4, doctorBean.getDateOfJoining());
            ps.setString(5, doctorBean.getGender());
            ps.setString(6, doctorBean.getQualification());
            ps.setString(7, doctorBean.getSpecialization());
            ps.setInt(8, doctorBean.getYearsOfExperience());
            ps.setString(9, doctorBean.getStreet());
            ps.setString(10, doctorBean.getLocation());
            ps.setString(11, doctorBean.getCity());
            ps.setString(12, doctorBean.getState());
            ps.setString(13, doctorBean.getPincode());
            ps.setString(14, doctorBean.getContactNumber());
            ps.setString(15, doctorBean.getEmailID());

            int rows = ps.executeUpdate();
            return rows > 0 ? "✅ Doctor added successfully!" : "❌ Failed to add doctor.";

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "⚠️ Error: " + e.getMessage();
        }
    }
    
    @Override
    public Boolean modifyDoctor(String doctorID, Map<String, String> updatedFields) {
        if (updatedFields.isEmpty()) return false;

        StringBuilder sql = new StringBuilder("UPDATE doctors SET ");
        int count = 0;

        for (String field : updatedFields.keySet()) {
            sql.append(field).append("=?");
            if (++count < updatedFields.size()) sql.append(", ");
        }
        sql.append(" WHERE doctorID=?");

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int index = 1;
            for (String field : updatedFields.keySet()) {
                ps.setString(index++, updatedFields.get(field));
            }
            ps.setString(index, doctorID);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    


    public ArrayList<DoctorBean> viewAllDoctors() {
        ArrayList<DoctorBean> doctorList = new ArrayList<>();
        String sql = "SELECT * FROM doctors";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DoctorBean d = new DoctorBean();
                d.setDoctorID(rs.getString("doctorID"));
                d.setDoctorName(rs.getString("doctorName"));
                d.setDateOfBirth(rs.getString("dateOfBirth"));
                d.setDateOfJoining(rs.getString("dateOfJoining"));
                d.setGender(rs.getString("gender"));
                d.setQualification(rs.getString("qualification"));
                d.setSpecialization(rs.getString("specialization"));
                d.setYearsOfExperience(rs.getInt("yearsOfExperience"));
                d.setStreet(rs.getString("street"));
                d.setLocation(rs.getString("location"));
                d.setCity(rs.getString("city"));
                d.setState(rs.getString("state"));
                d.setPincode(rs.getString("pincode"));
                d.setContactNumber(rs.getString("contactNumber"));
                d.setEmailID(rs.getString("emailID"));
                doctorList.add(d);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return doctorList;
    }


    @Override
    public int removeDoctor(String doctorID) {
        String sql = "DELETE FROM doctors WHERE doctorID=?";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, doctorID);
            return ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    
    public String addSchedule(ScheduleBean schedule) {
        String query = "INSERT INTO schedules (SCHEDULEID, DOCTORID, AVAILABLE_DAYS, SLOTS) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, schedule.getScheduleID());
            ps.setString(2, schedule.getDoctorID());
            ps.setString(3, schedule.getAvailableDays());
            ps.setString(4, schedule.getSlots());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0 ? "Schedule added successfully!" : "❌ Error in adding schedule!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "❌ Error: " + e.getMessage();
        }
    }
    
    
    public boolean modifySchedule(ScheduleBean schedule) {
        String query = "UPDATE schedules SET AVAILABLE_DAYS = ?, SLOTS = ? WHERE SCHEDULEID = ? AND DOCTORID = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, schedule.getAvailableDays());
            ps.setString(2, schedule.getSlots());
            ps.setString(3, schedule.getScheduleID());
            ps.setString(4, schedule.getDoctorID());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public int removeSchedule(String scheduleID, String doctorID) {
        String query = "DELETE FROM schedules WHERE SCHEDULEID = ? AND DOCTORID = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, scheduleID);
            ps.setString(2, doctorID);

            return ps.executeUpdate();  // Return number of rows deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    
    public ArrayList<String> viewSchedule(String doctorID) {
        ArrayList<String> scheduleDetails = new ArrayList<>();
        // SQL query to fetch schedule details along with doctor details
        String query = "SELECT s.SCHEDULEID, s.DOCTORID, s.AVAILABLE_DAYS, s.SLOTS, d.doctorName, d.specialization, d.city " +
                       "FROM schedules s " +
                       "JOIN doctors d ON s.DOCTORID = d.doctorID " +
                       "WHERE s.DOCTORID = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
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
    
    public ArrayList<String> getLeaveReports() {
        ArrayList<String> leaveReports = new ArrayList<>();
        // SQL query to fetch leave details along with doctor details
        String query = "SELECT l.LEAVEID, l.DOCTORID, l.LEAVE_FROM, l.LEAVE_TO, l.REASON, l.STATUS, " +
                       "d.doctorName, d.specialization, d.city " +
                       "FROM leaves l " +
                       "JOIN doctors d ON l.DOCTORID = d.doctorID";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            // Execute the query and store the result in a ResultSet
            ResultSet rs = ps.executeQuery();

            // Loop through the result set and create leave report strings
            while (rs.next()) {
                String leaveReport = "Leave ID: " + rs.getString("LEAVEID") + "\n" +
                                     "Doctor Name: " + rs.getString("doctorName") + "\n" +
                                     "Specialization: " + rs.getString("specialization") + "\n" +
                                     "City: " + rs.getString("city") + "\n" +
                                     "Leave From: " + rs.getDate("LEAVE_FROM") + "\n" +
                                     "Leave To: " + rs.getDate("LEAVE_TO") + "\n" +
                                     "Reason: " + rs.getString("REASON") + "\n" +
                                     "Status: " + (rs.getInt("STATUS") == 1 ? "Approved" : "Pending") + "\n\n";

                // Add the leave report string to the list
                leaveReports.add(leaveReport);
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Print the exception stack trace for debugging
        }

        return leaveReports;
    }

    
 // Method to fetch pending appointments
    public ArrayList<AppointmentBean> getPendingAppointments() {
        ArrayList<AppointmentBean> appointmentList = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE STATUS = 'PENDING'";  // Assuming 'PENDING' is the status

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AppointmentBean appointment = new AppointmentBean();
                appointment.setAppointmentID(rs.getString("APPOINTMENTID"));
                appointment.setDoctorID(rs.getString("DOCTORID"));
                appointment.setPatientID(rs.getString("PATIENTID"));
                appointment.setAppointmentDate(rs.getString("APPOINTMENT_DATE"));
                appointment.setAppointmentTime(rs.getString("APPOINTMENT_TIME"));
                appointmentList.add(appointment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }

    
    
 // Method to approve an appointment
    public boolean approveAppointment(String appointmentID) {
        String sql = "UPDATE appointments SET STATUS = 'APPROVED' WHERE APPOINTMENTID = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, appointmentID);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
 // Method to reject an appointment
    public boolean rejectAppointment(String appointmentID) {
        String sql = "UPDATE appointments SET STATUS = 'REJECTED' WHERE APPOINTMENTID = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, appointmentID);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public ArrayList<DoctorBean> suggestDoctors(String patientId, String date) {
        return null;
    }

    @Override
    public Map<PatientBean, AppointmentBean> viewPatientsByDate(String appointmentDate) {
        return null;
    }
}
