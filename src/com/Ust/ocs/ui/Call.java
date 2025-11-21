




package com.Ust.ocs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.Ust.ocs.bean.AppointmentBean;
import com.Ust.ocs.bean.CredentialsBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.bean.ProfileBean;
import com.Ust.ocs.bean.ScheduleBean;
import com.Ust.ocs.dao.AdministratorDAO;
import com.Ust.ocs.dao.PatientDAO;
import com.Ust.ocs.util.Authentication;

public class Call {
    private JFrame frame;
    private JTextField userText;              // Username field
    private JPasswordField passwordField;     // Password field

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Call window = new Call();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Call() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Login Form
        JLabel lblUsername = new JLabel("User ID:");
        lblUsername.setBounds(66, 58, 85, 14);
        frame.getContentPane().add(lblUsername);

        userText = new JTextField();
        userText.setBounds(161, 55, 130, 20);
        frame.getContentPane().add(userText);
        userText.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(66, 106, 85, 14);
        frame.getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(161, 103, 130, 20);
        frame.getContentPane().add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(161, 151, 89, 23);
        frame.getContentPane().add(btnLogin);

        // Register Button
        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(161, 185, 89, 23);
        frame.getContentPane().add(btnRegister);

        // Login Action
        btnLogin.addActionListener(e -> {
            String userId = userText.getText();
            String password = new String(passwordField.getPassword());

            CredentialsBean user = new CredentialsBean(userId, password, "", 0);
            Authentication auth = new AuthenticationImpl();

            if (auth.authenticate(user)) {
                JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + user.getUserId() + " (" + user.getUserType() + ")");
                if (user.getUserType().equalsIgnoreCase("Admin")) {
                    showAdminMenu();
                } else if (user.getUserType().equalsIgnoreCase("Patient")) {
                    PatientMenu.main(new String[]{}); 
                    frame.dispose();
                } else if (user.getUserType().equalsIgnoreCase("Reporter")) {
                    ReporterMenu.main(new String[]{}); 
                    frame.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "❌ Invalid credentials. Please try again.");
            }
        });

        // Register Action
        btnRegister.addActionListener(e -> {
            RegisterForm.main(new String[]{}); // Show registration form
            frame.dispose();  // Close the login window
        });
    }




    private void showAdminMenu() {
        // Create JFrame
        JFrame adminFrame = new JFrame("Admin Menu");
        adminFrame.setBounds(100, 100, 600, 500);  // Increased the frame size to 600x500
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame on the screen
        adminFrame.setLocationRelativeTo(null);  // This will center the frame

        // Set GridBagLayout for better control over the positioning
        adminFrame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);  // Add padding between components

        // Welcome Message at the top
        JLabel welcomeLabel = new JLabel("Welcome to Admin Panel", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Increased font size for welcome message
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Span across both columns
        gbc.fill = GridBagConstraints.HORIZONTAL;
        adminFrame.getContentPane().add(welcomeLabel, gbc);

        // Buttons for different admin functions
        JButton addDoctorBtn = new JButton("Add Doctor");
        JButton modifyDoctorBtn = new JButton("Modify Doctor");
        JButton viewDoctorsBtn = new JButton("View Doctors");
        JButton removeDoctorBtn = new JButton("Remove Doctor");
        JButton manageScheduleBtn = new JButton("Manage Doctor Schedule");
        JButton showLeaveReportBtn = new JButton("Show Leave Report");
        JButton manageAppointmentsBtn = new JButton("Manage Appointments");

        addDoctorBtn.setFont(new Font("Arial", Font.PLAIN, 18));  // Increased button font size
        modifyDoctorBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        viewDoctorsBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        removeDoctorBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        manageScheduleBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        showLeaveReportBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        manageAppointmentsBtn.setFont(new Font("Arial", Font.PLAIN, 18));

        addDoctorBtn.addActionListener(e -> addDoctor());
        modifyDoctorBtn.addActionListener(e -> modifyDoctor());
        viewDoctorsBtn.addActionListener(e -> viewDoctors());
        removeDoctorBtn.addActionListener(e -> removeDoctor());
        manageScheduleBtn.addActionListener(e -> manageSchedule());
        showLeaveReportBtn.addActionListener(e -> showLeaveReport());
        manageAppointmentsBtn.addActionListener(e -> manageAppointments());  // New button for appointment management

        // Set GridBagConstraints for buttons (arranging buttons in two columns)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Reset the span for the next buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;
        adminFrame.getContentPane().add(addDoctorBtn, gbc);

        gbc.gridx = 1;
        adminFrame.getContentPane().add(removeDoctorBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        adminFrame.getContentPane().add(viewDoctorsBtn, gbc);

        gbc.gridx = 1;
        adminFrame.getContentPane().add(modifyDoctorBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        adminFrame.getContentPane().add(manageScheduleBtn, gbc);

        gbc.gridx = 1;
        adminFrame.getContentPane().add(showLeaveReportBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;  // Make this button span both columns
        adminFrame.getContentPane().add(manageAppointmentsBtn, gbc);

        // Show the frame
        adminFrame.setVisible(true);
    }


    
    private void manageAppointments() {
        // Fetch pending appointments from the database
        ArrayList<AppointmentBean> pendingAppointments = new AdministratorDAO().getPendingAppointments();
        
        if (pendingAppointments.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No pending appointments.", "No Appointments", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Create a list to display the pending appointments
        StringBuilder appointmentDetails = new StringBuilder();
        for (AppointmentBean appointment : pendingAppointments) {
            appointmentDetails.append("Appointment ID: ").append(appointment.getAppointmentID()).append("\n")
                    .append("Doctor ID: ").append(appointment.getDoctorID()).append("\n")
                    .append("Patient ID: ").append(appointment.getPatientID()).append("\n")
                    .append("Date: ").append(appointment.getAppointmentDate()).append("\n")
                    .append("Time: ").append(appointment.getAppointmentTime()).append("\n\n");
        }

        // Show the appointments in a dialog
        JTextArea appointmentTextArea = new JTextArea(appointmentDetails.toString());
        appointmentTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(appointmentTextArea);
        int option = JOptionPane.showConfirmDialog(null, scrollPane, "Pending Appointments", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String appointmentID = JOptionPane.showInputDialog("Enter Appointment ID to approve/reject:");

            // Ask for approval or rejection
            int action = JOptionPane.showConfirmDialog(null, "Do you want to approve this appointment?", "Approve Appointment", JOptionPane.YES_NO_OPTION);

            if (action == JOptionPane.YES_OPTION) {
                boolean result = new AdministratorDAO().approveAppointment(appointmentID);
                JOptionPane.showMessageDialog(null, result ? "Appointment approved!" : "Failed to approve appointment.");
            } else if (action == JOptionPane.NO_OPTION) {
                boolean result = new AdministratorDAO().rejectAppointment(appointmentID);
                JOptionPane.showMessageDialog(null, result ? "Appointment rejected!" : "Failed to reject appointment.");
            }
        }
    }



    private void addDoctor() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        JTextField doctorIDField = new JTextField();
        JTextField doctorNameField = new JTextField();
        JTextField specializationField = new JTextField();
        JTextField yearsOfExperienceField = new JTextField();
        JTextField dateOfBirthField = new JTextField();
        JTextField dateOfJoiningField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField qualificationField = new JTextField();
        JTextField streetField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField cityField = new JTextField();
        JTextField stateField = new JTextField();
        JTextField pincodeField = new JTextField();
        JTextField contactNumberField = new JTextField();
        JTextField emailField = new JTextField();

        panel.add(new JLabel("Doctor ID:"));
        panel.add(doctorIDField);
        panel.add(new JLabel("Doctor Name:"));
        panel.add(doctorNameField);
        panel.add(new JLabel("Specialization:"));
        panel.add(specializationField);
        panel.add(new JLabel("Years of Experience:"));
        panel.add(yearsOfExperienceField);
        panel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        panel.add(dateOfBirthField);
        panel.add(new JLabel("Date of Joining (YYYY-MM-DD):"));
        panel.add(dateOfJoiningField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderField);
        panel.add(new JLabel("Qualification:"));
        panel.add(qualificationField);
        panel.add(new JLabel("Street:"));
        panel.add(streetField);
        panel.add(new JLabel("Location:"));
        panel.add(locationField);
        panel.add(new JLabel("City:"));
        panel.add(cityField);
        panel.add(new JLabel("State:"));
        panel.add(stateField);
        panel.add(new JLabel("Pincode:"));
        panel.add(pincodeField);
        panel.add(new JLabel("Contact Number:"));
        panel.add(contactNumberField);
        panel.add(new JLabel("Email ID:"));
        panel.add(emailField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Enter Doctor Details", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            DoctorBean doctor = new DoctorBean();
            doctor.setDoctorID(doctorIDField.getText());
            doctor.setDoctorName(doctorNameField.getText());
            doctor.setSpecialization(specializationField.getText());
            doctor.setYearsOfExperience(Integer.parseInt(yearsOfExperienceField.getText()));
            doctor.setDateOfBirth(dateOfBirthField.getText());
            doctor.setDateOfJoining(dateOfJoiningField.getText());
            doctor.setGender(genderField.getText());
            doctor.setQualification(qualificationField.getText());
            doctor.setStreet(streetField.getText());
            doctor.setLocation(locationField.getText());
            doctor.setCity(cityField.getText());
            doctor.setState(stateField.getText());
            doctor.setPincode(pincodeField.getText());
            doctor.setContactNumber(contactNumberField.getText());
            doctor.setEmailID(emailField.getText());

            AdministratorDAO adminDAO = new AdministratorDAO();
            String result = adminDAO.addDoctor(doctor);
            JOptionPane.showMessageDialog(frame, result);
        }
    }

    private void modifyDoctor() {
        String doctorID = JOptionPane.showInputDialog("Enter Doctor ID to Modify");
        HashMap<String, String> updatedFields = new HashMap<>();

        String[] fieldOptions = {
                "doctorName", "specialization", "yearsOfExperience", "dateOfBirth", "dateOfJoining", "gender", "qualification", 
                "street", "location", "city", "state", "pincode", "contactNumber", "emailID"
        };

        for (String field : fieldOptions) {
            String newValue = JOptionPane.showInputDialog("Enter new " + field + ":");
            updatedFields.put(field, newValue);
        }

        AdministratorDAO adminDAO = new AdministratorDAO();
        boolean modified = adminDAO.modifyDoctor(doctorID, updatedFields);
        if (modified) {
            JOptionPane.showMessageDialog(frame, "Doctor details updated successfully!");
        } else {
            JOptionPane.showMessageDialog(frame, "❌ Doctor ID not found or no changes made!");
        }
    }

    private void viewDoctors() {
        AdministratorDAO adminDAO = new AdministratorDAO();
        ArrayList<DoctorBean> doctors = adminDAO.viewAllDoctors();

        StringBuilder sb = new StringBuilder();
        for (DoctorBean doctor : doctors) {
            sb.append("Doctor ID: ").append(doctor.getDoctorID()).append("\n");
            sb.append("Name: ").append(doctor.getDoctorName()).append("\n");
            sb.append("Specialization: ").append(doctor.getSpecialization()).append("\n\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(frame, scrollPane);
    }

    private void removeDoctor() {
        String doctorID = JOptionPane.showInputDialog("Enter Doctor ID to Remove");
        AdministratorDAO adminDAO = new AdministratorDAO();
        int result = adminDAO.removeDoctor(doctorID);
        if (result > 0) {
            JOptionPane.showMessageDialog(frame, "Doctor removed successfully!");
        } else {
            JOptionPane.showMessageDialog(frame, "❌ Doctor ID not found!");
        }
    }
    private void addSchedule() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        JTextField scheduleIDField = new JTextField();
        JTextField doctorIDField = new JTextField();
        JTextField availableDaysField = new JTextField();
        JTextField slotsField = new JTextField();

        panel.add(new JLabel("Schedule ID:"));
        panel.add(scheduleIDField);
        panel.add(new JLabel("Doctor ID:"));
        panel.add(doctorIDField);
        panel.add(new JLabel("Available Days (e.g. Monday, Wednesday):"));
        panel.add(availableDaysField);
        panel.add(new JLabel("Time Slots (e.g. 9:00 AM - 10:00 AM):"));
        panel.add(slotsField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Enter Schedule Details", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            ScheduleBean schedule = new ScheduleBean();
            schedule.setScheduleID(scheduleIDField.getText());
            schedule.setDoctorID(doctorIDField.getText());
            schedule.setAvailableDays(availableDaysField.getText());
            schedule.setSlots(slotsField.getText());

            AdministratorDAO adminDAO = new AdministratorDAO();
            String result = adminDAO.addSchedule(schedule);
            JOptionPane.showMessageDialog(null, result);
        }
    }
    
    private void manageSchedule() {
        String[] options = {"Add Schedule", "Modify Schedule", "Remove Schedule", "View Schedule"};
        String choice = (String) JOptionPane.showInputDialog(null, "Choose an action:", "Schedule Management",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice != null) {
            switch (choice) {
                case "Add Schedule":
                    addSchedule();  // Function to add schedule
                    break;
                case "Modify Schedule":
                    modifySchedule();  // Function to modify schedule
                    break;
                case "Remove Schedule":
                    removeSchedule();  // Function to remove schedule
                    break;
                case "View Schedule":
                	viewDoctorSchedule();  // Function to view schedules
                    break;
            }
        }
    }
    
    private void modifySchedule() {
        String scheduleID = JOptionPane.showInputDialog("Enter Schedule ID to Modify");
        String doctorID = JOptionPane.showInputDialog("Enter Doctor ID");

        // Prompt for the fields to modify
        String newAvailableDays = JOptionPane.showInputDialog("Enter new Available Days:");
        String newSlots = JOptionPane.showInputDialog("Enter new Time Slots:");

        ScheduleBean schedule = new ScheduleBean();
        schedule.setScheduleID(scheduleID);
        schedule.setDoctorID(doctorID);
        schedule.setAvailableDays(newAvailableDays);
        schedule.setSlots(newSlots);

        AdministratorDAO adminDAO = new AdministratorDAO();
        boolean modified = adminDAO.modifySchedule(schedule);
        if (modified) {
            JOptionPane.showMessageDialog(null, "Schedule updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "❌ Schedule ID or Doctor ID not found!");
        }
    }

    private void removeSchedule() {
        String scheduleID = JOptionPane.showInputDialog("Enter Schedule ID to Remove");
        String doctorID = JOptionPane.showInputDialog("Enter Doctor ID");

        AdministratorDAO adminDAO = new AdministratorDAO();
        int result = adminDAO.removeSchedule(scheduleID, doctorID);
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "Schedule removed successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "❌ Schedule ID or Doctor ID not found!");
        }
    }

    private void viewDoctorSchedule() {
        String doctorID = JOptionPane.showInputDialog("Enter Doctor ID to View Schedule");

        // Call the viewSchedule method to get the doctor's schedule
        AdministratorDAO adminDAO = new AdministratorDAO();
        ArrayList<String> scheduleDetails = adminDAO.viewSchedule(doctorID);

        // Display the schedule in a dialog box
        if (scheduleDetails.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No schedule found for Doctor ID: " + doctorID);
        } else {
            StringBuilder sb = new StringBuilder();
            for (String schedule : scheduleDetails) {
                sb.append(schedule);
            }

            JTextArea textArea = new JTextArea(sb.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(null, scrollPane, "Doctor's Schedule", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void showLeaveReport() {
        // Fetch all leave reports from the DAO
        AdministratorDAO adminDAO = new AdministratorDAO();
        ArrayList<String> leaveReports = adminDAO.getLeaveReports();

        // Display the leave reports in a dialog box
        if (leaveReports.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No leave reports found.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String report : leaveReports) {
                sb.append(report).append("\n");
            }

            JTextArea textArea = new JTextArea(sb.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(null, scrollPane, "Leave Report", JOptionPane.INFORMATION_MESSAGE);
        }
    }



}

