package com.Ust.ocs.ui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.ScheduleBean;
import com.Ust.ocs.dao.ReporterDAO;

public class ReporterMenu {
    private JFrame frame; // Declare frame globally to access it in other methods

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ReporterMenu window = new ReporterMenu(); // Create instance
                window.frame.setVisible(true); // Ensure frame is visible after initialization
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor to initialize the frame
    public ReporterMenu() {
        initialize(); // Call initialize method to set up the UI components
    }

    private void initialize() {
        frame = new JFrame("Reporter Menu"); // Initialize the frame here
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        JButton reportLeaveBtn = new JButton("Report Leave");
        JButton viewDoctorsBtn = new JButton("View Doctors");
        JButton viewAvailableDoctorsBtn = new JButton("View Available Doctors on Specific Date");
        JButton removeLeaveBtn = new JButton("Remove Leave");
        JButton viewDoctorsOnLeaveBtn = new JButton("View Doctors on Leave");  // New button to view doctors on leave

        // Add action listeners for each button
        reportLeaveBtn.addActionListener(e -> reportLeave());
        viewDoctorsBtn.addActionListener(e -> viewAllDoctors());
        viewAvailableDoctorsBtn.addActionListener(e -> viewAvailableDoctors());
        removeLeaveBtn.addActionListener(e -> removeLeave());
        viewDoctorsOnLeaveBtn.addActionListener(e -> showDoctorsOnLeave());  // Action listener for new button

        // Add buttons to the frame
        frame.getContentPane().add(reportLeaveBtn);
        frame.getContentPane().add(viewDoctorsBtn);
        frame.getContentPane().add(viewAvailableDoctorsBtn);
        frame.getContentPane().add(removeLeaveBtn);
        frame.getContentPane().add(viewDoctorsOnLeaveBtn);  // Add the new button for "View Doctors on Leave"

        frame.setVisible(true);  // Show the frame
    }

    // Define your methods here (like reportLeave(), viewAllDoctors(), etc.)





    private void viewAllDoctors() {
        ReporterDAO reporterDAO = new ReporterDAO();
        ArrayList<DoctorBean> doctorList = reporterDAO.viewAllDoctors();

        // Create a list model to hold the doctor details
        DefaultListModel<String> doctorListModel = new DefaultListModel<>();
        for (DoctorBean doctor : doctorList) {
            // Add doctor details to the list model (you can customize this as needed)
            doctorListModel.addElement(doctor.getDoctorID() + " - " + doctor.getDoctorName() + " (" + doctor.getSpecialization() + ")");
        }

        // Create a JList and populate it with the doctor details
        JList<String> doctorJList = new JList<>(doctorListModel);

        // Display the JList in a JScrollPane inside a JOptionPane
        JOptionPane.showMessageDialog(frame, new JScrollPane(doctorJList), "All Doctors", JOptionPane.INFORMATION_MESSAGE);
    }


    // Method to view available doctors on a specific date
    private void viewAvailableDoctors() {
        String date = JOptionPane.showInputDialog(frame, "Enter the date to check available doctors (yyyy-mm-dd):");

        if (date != null && !date.isEmpty()) {
            ReporterDAO reporterDAO = new ReporterDAO();
            ArrayList<DoctorBean> doctorList = reporterDAO.viewAvailableDoctors(date);

            DefaultListModel<String> doctorListModel = new DefaultListModel<>();
            for (DoctorBean doctor : doctorList) {
                doctorListModel.addElement(doctor.getDoctorID() + " - " + doctor.getDoctorName() + " (" + doctor.getSpecialization() + ")");
            }

            JList<String> doctorJList = new JList<>(doctorListModel);
            JOptionPane.showMessageDialog(frame, new JScrollPane(doctorJList), "Available Doctors", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a valid date.");
        }
    }

 // Method to report doctor's leave
    private void reportLeave() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField leaveIDField = new JTextField();
        JTextField doctorIDField = new JTextField();
        JTextField leaveReasonField = new JTextField();
        JTextField leaveFromField = new JTextField();
        JTextField leaveToField = new JTextField();

        panel.add(new JLabel("Enter Leave ID:"));
        panel.add(leaveIDField);
        panel.add(new JLabel("Enter Doctor ID:"));
        panel.add(doctorIDField);
        panel.add(new JLabel("Enter Leave Reason:"));
        panel.add(leaveReasonField);
        panel.add(new JLabel("Enter Leave Start Date (YYYY-MM-DD):"));
        panel.add(leaveFromField);
        panel.add(new JLabel("Enter Leave End Date (YYYY-MM-DD):"));
        panel.add(leaveToField);

        int option = JOptionPane.showConfirmDialog(frame, panel, "Report Doctor's Leave", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String leaveID = leaveIDField.getText().trim();
            String doctorID = doctorIDField.getText().trim();
            String leaveReason = leaveReasonField.getText().trim();
            String leaveFrom = leaveFromField.getText().trim();
            String leaveTo = leaveToField.getText().trim();

            if (!leaveID.isEmpty() && !doctorID.isEmpty() && !leaveReason.isEmpty() && !leaveFrom.isEmpty() && !leaveTo.isEmpty()) {
                try {
                    Date startDate = Date.valueOf(leaveFrom);
                    Date endDate = Date.valueOf(leaveTo);

                    // Check if the end date is before the start date
                    if (endDate.before(startDate)) {
                        JOptionPane.showMessageDialog(frame, "End date cannot be before start date.");
                        return;
                    }

                    // Now we call the updated DAO method that inserts into the leaves table
                    ReporterDAO reporterDAO = new ReporterDAO();
                    boolean success = reporterDAO.reportLeave(leaveID, doctorID, leaveReason, startDate, endDate, 1);  // status = 1 (Has appointments)

                    if (success) {
                        JOptionPane.showMessageDialog(frame, "Leave report submitted for Doctor ID: " + doctorID + "\nReason: " + leaveReason);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error in reporting leave. Please try again.");
                    }

                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(frame, "Invalid date format. Please use 'YYYY-MM-DD'.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
            }
        }
    }


    private void removeLeave() {
        // Ask the Reporter for the LEAVEID of the leave they want to remove
        String leaveID = JOptionPane.showInputDialog("Enter Leave ID to Remove:");

        if (leaveID != null && !leaveID.trim().isEmpty()) {
            // Call the DAO to remove the leave
            ReporterDAO reporterDAO = new ReporterDAO();
            boolean success = reporterDAO.removeLeave(leaveID);

            if (success) {
                JOptionPane.showMessageDialog(null, "Leave with ID " + leaveID + " has been successfully removed.");
            } else {
                JOptionPane.showMessageDialog(null, "❌ Leave ID " + leaveID + " not found or could not be removed.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a valid Leave ID.");
        }
    }
    
    
    private void showDoctorsOnLeave() {
        ReporterDAO reporterDAO = new ReporterDAO();
        ArrayList<String> doctorsOnLeave = reporterDAO.viewDoctorsOnLeave();  // Fetch doctors on leave

        StringBuilder sb = new StringBuilder();
        for (String doctorInfo : doctorsOnLeave) {
            sb.append(doctorInfo);  // Append each doctor's info
        }

        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(frame, scrollPane, "Doctors on Leave", JOptionPane.INFORMATION_MESSAGE);
    }



}


