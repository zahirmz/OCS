




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
        frame = new JFrame("Login");
        frame.setBounds(100, 100, 450, 350);  // Set the initial size of the window

        // Center the window on the screen
        frame.setLocationRelativeTo(null);  // This will center the frame

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Set background color for the frame
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

        // Title label
        JLabel lblTitle = new JLabel("Welcome to the System");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(120, 20, 250, 30);
        frame.getContentPane().add(lblTitle);

        // User ID Label
        JLabel lblUsername = new JLabel("User ID:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsername.setBounds(66, 80, 85, 20);
        frame.getContentPane().add(lblUsername);

        // User ID Text Field
        userText = new JTextField();
        userText.setFont(new Font("Arial", Font.PLAIN, 14));
        userText.setBounds(161, 80, 200, 30);
        userText.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
        frame.getContentPane().add(userText);

        // Password Label
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPassword.setBounds(66, 120, 85, 20);
        frame.getContentPane().add(lblPassword);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBounds(161, 120, 200, 30);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
        frame.getContentPane().add(passwordField);

        // Login Button
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(new Color(56, 128, 255)); // Blue color for button
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBounds(161, 170, 90, 35);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Button Hover Effect
        btnLogin.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(new Color(85, 185, 255)); // Lighter blue
            }

            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(new Color(56, 128, 255)); // Original color
            }
        });

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
        frame.getContentPane().add(btnLogin);

        // Register Button
        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegister.setBackground(new Color(34, 193, 195)); // Aqua color for the Register button
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBounds(161, 215, 90, 35);
        btnRegister.setFocusPainted(false);
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Register Button Hover Effect
        btnRegister.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnRegister.setBackground(new Color(85, 255, 255)); // Lighter aqua color
            }

            public void mouseExited(MouseEvent e) {
                btnRegister.setBackground(new Color(34, 193, 195)); // Original color
            }
        });

        btnRegister.addActionListener(e -> {
            RegisterForm.main(new String[]{});
            frame.dispose();
        });
        frame.getContentPane().add(btnRegister);
        
        // Add a logo or an icon at the top (optional)
        JLabel lblLogo = new JLabel();
        lblLogo.setIcon(new ImageIcon("path_to_logo.png")); // Optional logo
        lblLogo.setBounds(180, 10, 100, 50);
        frame.getContentPane().add(lblLogo);

        // Show the frame
        frame.setVisible(true);
    }



    private void showAdminMenu() {
        // Create JFrame
        JFrame adminFrame = new JFrame("Admin Panel");
        adminFrame.setBounds(100, 100, 600, 500);  // Increased frame size
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame
        adminFrame.setLocationRelativeTo(null);  // Center the frame on screen

        // Set background color for the panel
        adminFrame.getContentPane().setBackground(new Color(255, 255, 255));

        // Use GridBagLayout for better control over component placement
        adminFrame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);  // Add padding between components

        // Welcome Message at the top
        JLabel welcomeLabel = new JLabel("Welcome to Admin Panel", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));  // Larger font size for the welcome message
        welcomeLabel.setForeground(new Color(50, 50, 50));  // Dark gray color for text
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Span across both columns
        gbc.fill = GridBagConstraints.HORIZONTAL;
        adminFrame.getContentPane().add(welcomeLabel, gbc);

        // Define buttons for admin actions
        JButton addDoctorBtn = createButton("Add Doctor");
        JButton modifyDoctorBtn = createButton("Modify Doctor");
        JButton viewDoctorsBtn = createButton("View Doctors");
        JButton removeDoctorBtn = createButton("Remove Doctor");
        JButton manageScheduleBtn = createButton("Manage Doctor Schedule");
        JButton showLeaveReportBtn = createButton("Show Leave Report");
        JButton manageAppointmentsBtn = createButton("Manage Appointments");

        // Set action listeners
        addDoctorBtn.addActionListener(e -> addDoctor());
        modifyDoctorBtn.addActionListener(e -> modifyDoctor());
        viewDoctorsBtn.addActionListener(e -> viewDoctors());
        removeDoctorBtn.addActionListener(e -> removeDoctor());
        manageScheduleBtn.addActionListener(e -> manageSchedule());
        showLeaveReportBtn.addActionListener(e -> showLeaveReport());
        manageAppointmentsBtn.addActionListener(e -> manageAppointments());

        // Set GridBagConstraints for buttons (arrange in two columns)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Reset the span for next buttons
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

    // Helper method to create styled buttons
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBackground(new Color(70, 130, 180));  // SteelBlue color
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(250, 50));  // Set button size
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Hand cursor on hover

        // Add hover effect for buttons
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(100, 149, 237));  // Lighter blue when hovered
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(70, 130, 180));  // Back to original color
            }
        });

        return button;
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

