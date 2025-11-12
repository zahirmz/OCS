package com.Ust.ocs.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

import com.Ust.ocs.bean.AppointmentBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.PatientBean;
import com.Ust.ocs.service.Administrator;
import com.Ust.ocs.util.DBUtil;

public class AdministratorDAO implements Administrator {

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


    // ✅ Dynamic Field Update (for partial modification)
//    @Override
//    public Boolean modifyDoctor(String doctorID, Map<String, String> updatedFields) {
//        if (updatedFields.isEmpty()) return false;
//
//        StringBuilder sql = new StringBuilder("UPDATE doctors SET ");
//        int count = 0;
//
//        for (String field : updatedFields.keySet()) {
//            sql.append(field).append("=?");
//            if (++count < updatedFields.size()) sql.append(", ");
//        }
//        sql.append(" WHERE doctorID=?");
//
//        try (Connection con = DBUtil.getDBConnection();
//             PreparedStatement ps = con.prepareStatement(sql.toString())) {
//
//            int index = 1;
//            for (String field : updatedFields.keySet()) {
//                ps.setString(index++, updatedFields.get(field));
//            }
//            ps.setString(index, doctorID);
//
//            int rows = ps.executeUpdate();
//            return rows > 0;
//
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

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

    @Override
    public ArrayList<DoctorBean> suggestDoctors(String patientId, String date) {
        return null;
    }

    @Override
    public Map<PatientBean, AppointmentBean> viewPatientsByDate(String appointmentDate) {
        return null;
    }
}
