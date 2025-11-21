package com.Ust.ocs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import com.Ust.ocs.bean.AppointmentBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.PatientBean;
import com.Ust.ocs.dao.PatientDAO;

public class PatientMenu {
    private static final String URL = "jdbc:mysql://localhost:3306/cbh"; // Replace with your database URL
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "pass@word1"; // Replace with your MySQL password

    private JFrame frame;
    private PatientDAO patientDAO;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PatientMenu window = new PatientMenu();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor to initialize the frame and PatientDAO
    public PatientMenu() {
        patientDAO = new PatientDAO();  // Initialize PatientDAO
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Patient Menu");
        frame.setBounds(100, 100, 600, 400);  // Adjusted window size to fit all buttons in three columns
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridBagLayout());  // Use GridBagLayout for better control

        // Create a GridBagConstraints object to control the layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Add padding between buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Ensure buttons stretch horizontally

        // First Column (View Doctors, View Schedule)
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0;
        JButton btnViewDoctors = new JButton("View ALL Available Doctors");
        btnViewDoctors.setPreferredSize(new Dimension(250, 40));  // Set button size
        btnViewDoctors.addActionListener(e -> viewAvailableDoctors());
        frame.getContentPane().add(btnViewDoctors, gbc);

        gbc.gridy = 1;
        JButton btnViewSchedule = new JButton("View Doctor Schedule");
        btnViewSchedule.setPreferredSize(new Dimension(250, 40));  // Set button size
        btnViewSchedule.addActionListener(e -> viewDoctorSchedule());
        frame.getContentPane().add(btnViewSchedule, gbc);

        // Second Column (Appointments)
        gbc.gridx = 1; // Column 1
        gbc.gridy = 0;
        JButton btnBookAppointment = new JButton("Book Appointment");
        btnBookAppointment.setPreferredSize(new Dimension(250, 40));  // Set button size
        btnBookAppointment.addActionListener(e -> bookAppointment());
        frame.getContentPane().add(btnBookAppointment, gbc);

        gbc.gridy = 1;
        JButton btnViewAppointments = new JButton("View Appointments");
        btnViewAppointments.setPreferredSize(new Dimension(250, 40));  // Set button size
        btnViewAppointments.addActionListener(e -> viewAppointments());
        frame.getContentPane().add(btnViewAppointments, gbc);

        gbc.gridy = 2;
        JButton btnCheckStatus = new JButton("Check Appointment Status");
        btnCheckStatus.setPreferredSize(new Dimension(250, 40));  // Set button size
        btnCheckStatus.addActionListener(e -> {
            String patientID = JOptionPane.showInputDialog(frame, "Enter your Patient ID:");
            checkAppointmentStatus(patientID);
        });
        frame.getContentPane().add(btnCheckStatus, gbc);

        // Third Column (Ailment details)
        gbc.gridx = 2; // Column 2
        gbc.gridy = 0;
        JButton btnAddAilmentDetails = new JButton("Add Ailment Details");
        btnAddAilmentDetails.setPreferredSize(new Dimension(250, 40));  // Set button size
        btnAddAilmentDetails.addActionListener(e -> addAilmentDetails());
        frame.getContentPane().add(btnAddAilmentDetails, gbc);

        gbc.gridy = 1;
        JButton btnModifyAilmentDetails = new JButton("Modify Ailment Details");
        btnModifyAilmentDetails.setPreferredSize(new Dimension(250, 40));  // Set button size
        btnModifyAilmentDetails.addActionListener(e -> modifyAilmentDetails());
        frame.getContentPane().add(btnModifyAilmentDetails, gbc);

        gbc.gridy = 2;
        JButton btnViewAilmentDetails = new JButton("View Ailment Details");
        btnViewAilmentDetails.setPreferredSize(new Dimension(250, 40));  // Set button size
        btnViewAilmentDetails.addActionListener(e -> viewAilmentDetails());
        frame.getContentPane().add(btnViewAilmentDetails, gbc);
    }


    private void viewAvailableDoctors() {
        ArrayList<DoctorBean> doctorList = patientDAO.viewAllAvailableDoctors("");

        // Creating a list model to show doctor names in a JList
        DefaultListModel<String> doctorListModel = new DefaultListModel<>();
        for (DoctorBean doctor : doctorList) {
            doctorListModel.addElement(doctor.getDoctorID() + " - " + doctor.getDoctorName() + " (" + doctor.getSpecialization() + ")");
        }

        // Display doctors in a list
        JList<String> doctorJList = new JList<>(doctorListModel);
        JOptionPane.showMessageDialog(frame, new JScrollPane(doctorJList), "Available Doctors", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewDoctorSchedule() {
        String doctorID = JOptionPane.showInputDialog(frame, "Enter Doctor ID to View Schedule");

        if (doctorID != null && !doctorID.isEmpty()) {
            // Call the viewSchedule method to get the doctor's schedule
            ArrayList<String> scheduleDetails = patientDAO.viewSchedule(doctorID);

            // Display the schedule in a dialog box
            if (scheduleDetails.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No schedule found for Doctor ID: " + doctorID);
            } else {
                StringBuilder sb = new StringBuilder();
                for (String schedule : scheduleDetails) {
                    sb.append(schedule);  // Add each schedule info to the string builder
                }

                JTextArea textArea = new JTextArea(sb.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                JOptionPane.showMessageDialog(frame, scrollPane, "Doctor's Schedule", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Doctor ID cannot be empty.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addAilmentDetails() {
        String patientId = JOptionPane.showInputDialog(frame, "Enter your Patient ID:");
        String userId = JOptionPane.showInputDialog(frame, "Enter your User ID:");
        String appointmentDate = JOptionPane.showInputDialog(frame, "Enter appointment date (yyyy-mm-dd):");
        String ailmentType = JOptionPane.showInputDialog(frame, "Enter ailment type:");
        String ailmentDetails = JOptionPane.showInputDialog(frame, "Enter ailment details:");
        String diagnosisHistory = JOptionPane.showInputDialog(frame, "Enter diagnosis history:");

        // Create a new PatientBean object and set the values
        PatientBean patientBean = new PatientBean();
        patientBean.setPatientID(patientId);
        patientBean.setUserID(userId);
        patientBean.setAppointmentDate(appointmentDate);
        patientBean.setAilmentType(ailmentType);
        patientBean.setAilmentDetails(ailmentDetails);
        patientBean.setDiagnosisHistory(diagnosisHistory);

        // Call PatientDAO to add the ailment details
        String result = patientDAO.addAilmentDetails(patientBean);
        JOptionPane.showMessageDialog(frame, result.equals("SUCCESS") ? "Ailment details added successfully." : "Failed to add ailment details.");
    }

    private void viewAilmentDetails() {
        String patientId = JOptionPane.showInputDialog(frame, "Enter your Patient ID:");
        
        // Fetch ailment details for the patient
        ArrayList<PatientBean> ailmentList = patientDAO.viewAilmentDetails(patientId);

        // If no records found, show a warning
        if (ailmentList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No ailment details found for this Patient ID.", "No Data", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Create a list model to display ailment details in JList
        DefaultListModel<String> ailmentListModel = new DefaultListModel<>();
        
        // Populate the list model with ailment details from the retrieved data
        for (PatientBean patient : ailmentList) {
            String ailmentInfo = "Ailment Type: " + patient.getAilmentType() +
                                 " | Diagnosis History: " + patient.getDiagnosisHistory();
            ailmentListModel.addElement(ailmentInfo);
        }

        // Display the ailment details in a scrollable list
        JList<String> ailmentJList = new JList<>(ailmentListModel);
        ailmentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // Optional: Limit to single selection

        // Show the list in a scroll pane
        JScrollPane scrollPane = new JScrollPane(ailmentJList);
        JOptionPane.showMessageDialog(frame, scrollPane, "Ailment Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void modifyAilmentDetails() {
        String patientId = JOptionPane.showInputDialog(frame, "Enter your Patient ID:");
        String ailmentType = JOptionPane.showInputDialog(frame, "Enter new ailment type:");
        String ailmentDetails = JOptionPane.showInputDialog(frame, "Enter new ailment details:");
        String diagnosisHistory = JOptionPane.showInputDialog(frame, "Enter new diagnosis history:");
        String appointmentDate = JOptionPane.showInputDialog(frame, "Enter new appointment date (yyyy-mm-dd):");

        // Create a new PatientBean object and set the values
        PatientBean patientBean = new PatientBean();
        patientBean.setPatientID(patientId);
        patientBean.setAilmentType(ailmentType);
        patientBean.setAilmentDetails(ailmentDetails);
        patientBean.setDiagnosisHistory(diagnosisHistory);
        patientBean.setAppointmentDate(appointmentDate);

        // Call PatientDAO to modify the ailment details
        boolean result = patientDAO.modifyAilmentDetails(patientBean);
        JOptionPane.showMessageDialog(frame, result ? "Ailment details updated successfully." : "Failed to update ailment details.");
    }

    private void bookAppointment() {
        // Get input from user
        String patientID = JOptionPane.showInputDialog(frame, "Enter your Patient ID:");
        String doctorID = JOptionPane.showInputDialog(frame, "Enter Doctor ID for appointment:");
        String appointmentDate = JOptionPane.showInputDialog(frame, "Enter appointment date (yyyy-mm-dd):");
        String appointmentTime = JOptionPane.showInputDialog(frame, "Enter appointment time (e.g. 10:00 AM):");

        // Call the DAO method to book the appointment
        String result = patientDAO.bookAppointment(patientID, doctorID, appointmentDate, appointmentTime);

        // Show the result in a dialog
        JOptionPane.showMessageDialog(frame, result);
    }

    public void checkAppointmentStatus(String patientID) {
        String query = "SELECT APPOINTMENTID, STATUS FROM appointments WHERE PATIENTID = ? ORDER BY APPOINTMENTID DESC LIMIT 1";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, patientID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String appointmentID = rs.getString("APPOINTMENTID");
                String status = rs.getString("STATUS");

                if ("approved".equals(status)) {
                    JOptionPane.showMessageDialog(frame, "Your appointment " + appointmentID + " has been approved.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Your appointment " + appointmentID + " is still pending approval.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "No appointment found for this Patient ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void viewAppointments() {
        // Ask the patient to enter their ID to view their appointments
        String patientID = JOptionPane.showInputDialog(frame, "Enter your Patient ID:");

        if (patientID != null && !patientID.isEmpty()) {
            // Call PatientDAO to get the list of appointments
            ArrayList<AppointmentBean> appointmentList = patientDAO.viewAppointments(patientID);

            // If no appointments found, show a message
            if (appointmentList.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No appointments found for this Patient ID.", "No Appointments", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Create a list model to display appointments in a JList
            DefaultListModel<String> appointmentListModel = new DefaultListModel<>();
            for (AppointmentBean appointment : appointmentList) {
                String appointmentInfo = "Appointment ID: " + appointment.getAppointmentID() +
                                         " | Doctor ID: " + appointment.getDoctorID() +
                                         " | Date: " + appointment.getAppointmentDate() +
                                         " | Time: " + appointment.getAppointmentTime();
                appointmentListModel.addElement(appointmentInfo);
            }

            // Display the list of appointments
            JList<String> appointmentJList = new JList<>(appointmentListModel);
            JScrollPane scrollPane = new JScrollPane(appointmentJList);
            JOptionPane.showMessageDialog(frame, scrollPane, "Appointments", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Patient ID cannot be empty.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }

}
