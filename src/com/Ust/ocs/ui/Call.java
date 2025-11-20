




package com.Ust.ocs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.Ust.ocs.bean.CredentialsBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.dao.AdministratorDAO;
import com.Ust.ocs.dao.PatientDAO;
import com.Ust.ocs.util.Authentication;

public class Call {
    private JFrame frame;
    private JTextField userText;
    private JPasswordField passwordField;
    private JTextField registerUserText;
    private JPasswordField registerPasswordField;
    private JTextField registerNameField;

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
        JLabel lblUsername = new JLabel("Username:");
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

        // Registration Form (Initially hidden)
        JPanel registerPanel = new JPanel();
        registerPanel.setBounds(66, 225, 300, 120);
        registerPanel.setLayout(new GridLayout(4, 2));
        registerPanel.setVisible(false);

        JLabel lblRegisterName = new JLabel("Full Name:");
        registerPanel.add(lblRegisterName);

        registerNameField = new JTextField();
        registerPanel.add(registerNameField);

        JLabel lblRegisterUsername = new JLabel("Username:");
        registerPanel.add(lblRegisterUsername);

        registerUserText = new JTextField();
        registerPanel.add(registerUserText);

        JLabel lblRegisterPassword = new JLabel("Password:");
        registerPanel.add(lblRegisterPassword);

        registerPasswordField = new JPasswordField();
        registerPanel.add(registerPasswordField);

        JButton btnSubmitRegistration = new JButton("Submit Registration");
        registerPanel.add(btnSubmitRegistration);

        frame.getContentPane().add(registerPanel);

        // Login Action
        btnLogin.addActionListener(e -> {
            String userId = userText.getText();
            String password = new String(passwordField.getPassword());
            CredentialsBean user = new CredentialsBean(userId, password, "", 0);
            Authentication auth = new AuthenticationImpl();

            if (auth.authenticate(user)) {
                JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + user.getUserId() + " (" + user.getUserType() + ")");
                
                // After successful login, navigate based on the user type
                if (user.getUserType().equalsIgnoreCase("Admin")) {
                    showAdminMenu();
                } else if (user.getUserType().equalsIgnoreCase("Patient")) {
                    // Launch Patient Menu after login
                    PatientMenu.main(new String[]{});  // Open the Patient Menu
                    frame.dispose();  // Close the login window
                } else if (user.getUserType().equalsIgnoreCase("Reporter")) {
                    // Launch Reporter Menu after login
                    ReporterMenu.main(new String[]{});  // Open the Reporter Menu
                    frame.dispose();  // Close the login window
                }
            } else {
                JOptionPane.showMessageDialog(frame, "❌ Invalid credentials. Please try again.");
            }
        });

        // Register Button Action
        btnRegister.addActionListener(e -> {
            // Show the registration panel
            registerPanel.setVisible(true);
        });

        // Submit Registration Action
        btnSubmitRegistration.addActionListener(e -> {
            String newUsername = registerUserText.getText();
            String newPassword = new String(registerPasswordField.getPassword());
            String fullName = registerNameField.getText();

            if (registerPatient(newUsername, newPassword, fullName)) {
                JOptionPane.showMessageDialog(frame, "Registration Successful! Please log in.");
                registerPanel.setVisible(false); // Hide the registration form
            } else {
                JOptionPane.showMessageDialog(frame, "❌ Registration failed. Please try again.");
            }
        });
    }

    // Method to handle patient registration
    private boolean registerPatient(String username, String password, String fullName) {
        // Here you can call the DAO to insert patient details into the database
        PatientDAO patientDAO = new PatientDAO();

        // Create the CredentialsBean for login details
        CredentialsBean newCredentials = new CredentialsBean(username, password, "Patient", 0);

        // Call the PatientDAO to insert the new credentials into the database
        return patientDAO.registerNewPatient(newCredentials);
    }

 






    private void showAdminMenu() {
        JFrame adminFrame = new JFrame("Admin Menu");
        adminFrame.setBounds(100, 100, 400, 300);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.getContentPane().setLayout(new FlowLayout());

        JButton addDoctorBtn = new JButton("Add Doctor");
        JButton modifyDoctorBtn = new JButton("Modify Doctor");
        JButton viewDoctorsBtn = new JButton("View Doctors");
        JButton removeDoctorBtn = new JButton("Remove Doctor");

        addDoctorBtn.addActionListener(e -> addDoctor());
        modifyDoctorBtn.addActionListener(e -> modifyDoctor());
        viewDoctorsBtn.addActionListener(e -> viewDoctors());
        removeDoctorBtn.addActionListener(e -> removeDoctor());

        adminFrame.getContentPane().add(addDoctorBtn);
        adminFrame.getContentPane().add(modifyDoctorBtn);
        adminFrame.getContentPane().add(viewDoctorsBtn);
        adminFrame.getContentPane().add(removeDoctorBtn);

        adminFrame.setVisible(true);
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
}

