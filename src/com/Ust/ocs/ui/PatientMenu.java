package com.Ust.ocs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.PatientBean;
import com.Ust.ocs.dao.PatientDAO;

public class PatientMenu {
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

    public PatientMenu() {
        patientDAO = new PatientDAO();  // Initialize PatientDAO
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Patient Menu");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        JButton btnViewDoctors = new JButton("View Available Doctors");
        btnViewDoctors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for viewing doctors
                viewAvailableDoctors();
            }
        });

        JButton btnBookAppointment = new JButton("Book Appointment");
        btnBookAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for booking appointment
                bookAppointment();
            }
        });

        JButton btnAddAilment = new JButton("Add Ailment Details");
        btnAddAilment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to add ailment details
                addAilmentDetails();
            }
        });

        JButton btnModifyAilment = new JButton("Modify Ailment Details");
        btnModifyAilment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to modify ailment details
                modifyAilmentDetails();
            }
        });

        JButton btnViewAilment = new JButton("View Ailment Details");
        btnViewAilment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to view ailment details
                viewAilmentDetails();
            }
        });

        frame.getContentPane().add(btnViewDoctors);
        frame.getContentPane().add(btnBookAppointment);
        frame.getContentPane().add(btnAddAilment);
        frame.getContentPane().add(btnModifyAilment);
        frame.getContentPane().add(btnViewAilment);
    }

    // Method to view available doctors
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

    // Method to book an appointment
    private void bookAppointment() {
        // Inputs for appointment details
        String patientId = JOptionPane.showInputDialog(frame, "Enter your Patient ID:");
        String doctorId = JOptionPane.showInputDialog(frame, "Enter Doctor ID for appointment:");
        String appointmentDate = JOptionPane.showInputDialog(frame, "Enter appointment date (yyyy-mm-dd):");

        // Here, you could call a method to actually book the appointment (e.g., inserting into a database)
        JOptionPane.showMessageDialog(frame, "Appointment booked successfully with Doctor ID: " + doctorId + " on " + appointmentDate);
    }

    // Method to add ailment details
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

    // Method to view ailment details
    private void viewAilmentDetails() {
        String patientId = JOptionPane.showInputDialog(frame, "Enter your Patient ID:");
        ArrayList<PatientBean> ailmentList = patientDAO.viewAilmentDetails(patientId);

        // Create a list model to display ailment details in JList
        DefaultListModel<String> ailmentListModel = new DefaultListModel<>();
        for (PatientBean patient : ailmentList) {
            ailmentListModel.addElement("Ailment Type: " + patient.getAilmentType() + " | Diagnosis History: " + patient.getDiagnosisHistory());
        }

        // Display ailment details in a list
        JList<String> ailmentJList = new JList<>(ailmentListModel);
        JOptionPane.showMessageDialog(frame, new JScrollPane(ailmentJList), "Ailment Details", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to modify ailment details
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
}

