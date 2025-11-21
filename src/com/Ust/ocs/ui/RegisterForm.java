package com.Ust.ocs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.Ust.ocs.bean.ProfileBean;
import com.Ust.ocs.bean.CredentialsBean;
import com.Ust.ocs.dao.PatientDAO;

public class RegisterForm {
    private JFrame frame;
    private JTextField userText;
    private JPasswordField passwordField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField dateOfBirthField;
    private JTextField genderField;
    private JTextField streetField;
    private JTextField locationField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField pincodeField;
    private JTextField mobileField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RegisterForm window = new RegisterForm();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RegisterForm() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 600);  // Increased height to accommodate more fields
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Registration Form Components
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

        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setBounds(66, 146, 85, 14);
        frame.getContentPane().add(lblFirstName);

        firstNameField = new JTextField();
        firstNameField.setBounds(161, 143, 130, 20);
        frame.getContentPane().add(firstNameField);

        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setBounds(66, 186, 85, 14);
        frame.getContentPane().add(lblLastName);

        lastNameField = new JTextField();
        lastNameField.setBounds(161, 183, 130, 20);
        frame.getContentPane().add(lastNameField);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(66, 226, 85, 14);
        frame.getContentPane().add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(161, 223, 130, 20);
        frame.getContentPane().add(emailField);

        JLabel lblDateOfBirth = new JLabel("Date of Birth:");
        lblDateOfBirth.setBounds(66, 266, 85, 14);
        frame.getContentPane().add(lblDateOfBirth);

        dateOfBirthField = new JTextField();
        dateOfBirthField.setBounds(161, 263, 130, 20);
        frame.getContentPane().add(dateOfBirthField);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(66, 306, 85, 14);
        frame.getContentPane().add(lblGender);

        genderField = new JTextField();
        genderField.setBounds(161, 303, 130, 20);
        frame.getContentPane().add(genderField);

        JLabel lblStreet = new JLabel("Street:");
        lblStreet.setBounds(66, 346, 85, 14);
        frame.getContentPane().add(lblStreet);

        streetField = new JTextField();
        streetField.setBounds(161, 343, 130, 20);
        frame.getContentPane().add(streetField);

        JLabel lblLocation = new JLabel("Location:");
        lblLocation.setBounds(66, 386, 85, 14);
        frame.getContentPane().add(lblLocation);

        locationField = new JTextField();
        locationField.setBounds(161, 383, 130, 20);
        frame.getContentPane().add(locationField);

        JLabel lblCity = new JLabel("City:");
        lblCity.setBounds(66, 426, 85, 14);
        frame.getContentPane().add(lblCity);

        cityField = new JTextField();
        cityField.setBounds(161, 423, 130, 20);
        frame.getContentPane().add(cityField);

        JLabel lblState = new JLabel("State:");
        lblState.setBounds(66, 466, 85, 14);
        frame.getContentPane().add(lblState);

        stateField = new JTextField();
        stateField.setBounds(161, 463, 130, 20);
        frame.getContentPane().add(stateField);

        JLabel lblPincode = new JLabel("Pincode:");
        lblPincode.setBounds(66, 506, 85, 14);
        frame.getContentPane().add(lblPincode);

        pincodeField = new JTextField();
        pincodeField.setBounds(161, 503, 130, 20);
        frame.getContentPane().add(pincodeField);

        JLabel lblMobile = new JLabel("Mobile No:");
        lblMobile.setBounds(66, 546, 85, 14);
        frame.getContentPane().add(lblMobile);

        mobileField = new JTextField();
        mobileField.setBounds(161, 543, 130, 20);
        frame.getContentPane().add(mobileField);

        JButton btnSubmitRegistration = new JButton("Submit Registration");
        btnSubmitRegistration.setBounds(161, 570, 150, 23);
        frame.getContentPane().add(btnSubmitRegistration);

        // Submit Registration Action
        btnSubmitRegistration.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordField.getPassword());
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String dateOfBirth = dateOfBirthField.getText();
            String gender = genderField.getText();
            String street = streetField.getText();
            String location = locationField.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String pincode = pincodeField.getText();
            String mobile = mobileField.getText();

            // Create Credentials and Profile Beans
            CredentialsBean newCredentials = new CredentialsBean(username, password, "Patient", 0);
            ProfileBean newProfile = new ProfileBean();
            newProfile.setUserID(username);
            newProfile.setFirstName(firstName);
            newProfile.setLastName(lastName);
            newProfile.setEmailID(email);
            newProfile.setDateOfBirth(dateOfBirth);
            newProfile.setGender(gender);
            newProfile.setStreet(street);
            newProfile.setLocation(location);
            newProfile.setCity(city);
            newProfile.setState(state);
            newProfile.setPincode(pincode);
            newProfile.setMobileNo(mobile);
            newProfile.setPassword(password);  // Since password is also part of Profile table

            // Insert into Credentials and Profile Tables
            PatientDAO patientDAO = new PatientDAO();

            boolean credentialsInserted = patientDAO.registerNewPatient(newCredentials);
            boolean profileInserted = patientDAO.addProfileDetails(newProfile);

            if (credentialsInserted && profileInserted) {
                JOptionPane.showMessageDialog(frame, "Registration Successful!");
                frame.dispose();  // Close the registration window
            } else {
                JOptionPane.showMessageDialog(frame, "❌ Registration failed. Please try again.");
            }
        });
    }
}
