//package com.Ust.ocs.ui;
//
//import java.util.*;
//import com.Ust.ocs.bean.CredentialsBean;
//import com.Ust.ocs.bean.DoctorBean;
//import com.Ust.ocs.dao.AdministratorDAO;
//import com.Ust.ocs.util.Authentication;
//
//public class Call {
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        Authentication auth = new AuthenticationImpl();
//        System.out.println("\nEnter Username: ");
//        String userId = sc.nextLine();
//        System.out.println("Enter Password: ");
//        String password = sc.nextLine();
//        CredentialsBean user = new CredentialsBean(userId, password, "", 0);
//
//        if (auth.authenticate(user)) {
//            System.out.println("Login successful! Welcome, " + user.getUserId() + " (" + user.getUserType() + ")");
//            System.out.println("Authenticated role: " + user.getUserType());
//            if (user.getUserType().equals("Administrator")) {
//                showAdminMenu(sc); 
//            } else {
//                System.out.println("❌ You do not have access to this system.");
//            }
//        } else {
//            System.out.println("❌ Invalid credentials. Please try again.");
//        }
//
//        sc.close();
//    }
//
//    public static void showAdminMenu(Scanner sc) {
//        AdministratorDAO adminDAO = new AdministratorDAO();
//        while (true) {  
//            System.out.println("\n--- Admin Menu ---");
//            System.out.println("1. Add Doctor");
//            System.out.println("2. Modify Doctor");
//            System.out.println("3. View Doctors");
//            System.out.println("4. Remove Doctor");
//            System.out.println("5. Exit");
//            System.out.print("Enter your choice: ");
//            int choice = sc.nextInt();
//            sc.nextLine();
//
//            switch (choice) {
//                case 1: 
//                    addDoctor(adminDAO, sc);
//                    break;
//                case 2: 
//                    modifyDoctor(adminDAO, sc);
//                    break;
//                case 3:
//                    viewDoctors(adminDAO);  // Simply call the viewDoctors method here
//                    break;
//                case 4:
//                    removeDoctor(adminDAO, sc);
//                    break;
//                case 5:
//                    System.out.println("Exiting... Goodbye!");
//                    return;
//                default:
//                    System.out.println("❌ Invalid choice. Please select a valid option.");
//            }
//        }
//    }
//    private static void modifyDoctor(AdministratorDAO adminDAO, Scanner sc) {
//        System.out.print("\nEnter Doctor ID to modify: ");
//        String doctorID = sc.nextLine();
//
//        System.out.print("Enter number of fields to modify: ");
//        int fieldCount = sc.nextInt();
//        sc.nextLine(); // clear newline
//
//        Map<String, String> updatedFields = new HashMap<>();
//
//        System.out.println("\nAvailable fields:");
//        System.out.println("1. doctorName");
//        System.out.println("2. specialization");
//        System.out.println("3. yearsOfExperience");
//        System.out.println("4. dateOfBirth");
//        System.out.println("5. dateOfJoining");
//        System.out.println("6. gender");
//        System.out.println("7. qualification");
//        System.out.println("8. street");
//        System.out.println("9. location");
//        System.out.println("10. city");
//        System.out.println("11. state");
//        System.out.println("12. pincode");
//        System.out.println("13. contactNumber");
//        System.out.println("14. emailID");
//
//        for (int i = 0; i < fieldCount; i++) {
//            System.out.print("\nEnter field number to modify: ");
//            int fieldChoice = sc.nextInt();
//            sc.nextLine(); // clear newline
//
//            String fieldName = getFieldName(fieldChoice);
//            if (fieldName == null) {
//                System.out.println("❌ Invalid field choice. Try again.");
//                i--;
//                continue;
//            }
//
//            System.out.print("Enter new value for " + fieldName + ": ");
//            String newValue = sc.nextLine();
//            updatedFields.put(fieldName, newValue);
//        }
//
//        boolean modified = adminDAO.modifyDoctor(doctorID, updatedFields);
//
//        if (modified) {
//            System.out.println("\n✅ Doctor details updated successfully!");
//        } else {
//            System.out.println("\n❌ Doctor ID not found or no changes made!");
//        }
//    }
//    private static String getFieldName(int choice) {
//        switch (choice) {
//            case 1: return "doctorName";          // doctor_name
//            case 2: return "specialization";      // specialization
//            case 3: return "yearsOfExperience";   // years_of_experience
//            case 4: return "dateOfBirth";         // date_of_birth
//            case 5: return "dateOfJoining";       // date_of_joining
//            case 6: return "gender";              // gender
//            case 7: return "qualification";       // qualification
//            case 8: return "street";              // street
//            case 9: return "location";            // location
//            case 10: return "city";               // city
//            case 11: return "state";              // state
//            case 12: return "pincode";            // pincode
//            case 13: return "contactNumber";      // contact_number
//            case 14: return "emailID";            // email_id
//            default: return null;                 // if invalid field choice
//        }
//    }
//
//
//    private static void addDoctor(AdministratorDAO adminDAO, Scanner sc) {
//        System.out.print("\nEnter number of doctors to add: ");
//        int n = sc.nextInt();
//        sc.nextLine();
//        for (int i = 0; i < n; i++) {
//            DoctorBean doctor = new DoctorBean();
//            System.out.println("\nEnter details for Doctor " + (i + 1) + ":");
//            System.out.print("Enter Doctor ID: ");
//            doctor.setDoctorID(sc.nextLine());
//            System.out.print("Enter Doctor Name: ");
//            doctor.setDoctorName(sc.nextLine());
//            System.out.print("Enter Specialization: ");
//            doctor.setSpecialization(sc.nextLine());
//            System.out.print("Enter Years of Experience: ");
//            doctor.setYearsOfExperience(sc.nextInt());
//            sc.nextLine();
//            System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
//            doctor.setDateOfBirth(sc.nextLine());
//            System.out.print("Enter Date of Joining (YYYY-MM-DD): ");
//            doctor.setDateOfJoining(sc.nextLine());
//            System.out.print("Enter Gender: ");
//            doctor.setGender(sc.nextLine());
//            System.out.print("Enter Qualification: ");
//            doctor.setQualification(sc.nextLine());
//            System.out.print("Enter Street Address: ");
//            doctor.setStreet(sc.nextLine());
//            System.out.print("Enter Location: ");
//            doctor.setLocation(sc.nextLine());
//            System.out.print("Enter City: ");
//            doctor.setCity(sc.nextLine());
//            System.out.print("Enter State: ");
//            doctor.setState(sc.nextLine());
//            System.out.print("Enter Pincode: ");
//            doctor.setPincode(sc.nextLine());
//            System.out.print("Enter Contact Number: ");
//            doctor.setContactNumber(sc.nextLine());
//            System.out.print("Enter Email ID: ");
//            doctor.setEmailID(sc.nextLine());
//
//            String result = adminDAO.addDoctor(doctor);
//            System.out.println(result);
//        }
//    }
//    
//    private static void viewDoctors(AdministratorDAO adminDAO) {
//        System.out.println("\n--- List of All Doctors ---");
//        ArrayList<DoctorBean> doctors = adminDAO.viewAllDoctors();
//        for (DoctorBean doctor : doctors) {
//            System.out.println(doctor); // This will call the overridden toString() method
//        }
//    }
//
//    private static void removeDoctor(AdministratorDAO adminDAO, Scanner sc) {
//        System.out.print("\nEnter Doctor ID to remove: ");
//        String removeID = sc.nextLine();
//
//        int result = adminDAO.removeDoctor(removeID);
//        if (result == 1) {
//            System.out.println("✅ Doctor removed successfully!");
//        } else {
//            System.out.println("❌ Doctor ID not found!");
//        }
//    }
//}




package com.Ust.ocs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.Ust.ocs.bean.CredentialsBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.dao.AdministratorDAO;
import com.Ust.ocs.util.Authentication;

public class Call {
    private JFrame frame;
    private JTextField userText;
    private JPasswordField passwordField;

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
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

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

        btnLogin.addActionListener(e -> {
            String userId = userText.getText();
            String password = new String(passwordField.getPassword());
            CredentialsBean user = new CredentialsBean(userId, password, "", 0);
            Authentication auth = new AuthenticationImpl();

            if (auth.authenticate(user)) {
                JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + user.getUserId() + " (" + user.getUserType() + ")");
                
                // Check user type and show the corresponding menu
                if (user.getUserType().equalsIgnoreCase("Admin")) {
                    showAdminMenu();
                } else if (user.getUserType().equalsIgnoreCase("Patient")) {
                    // Launch Patient Menu in a new window
                    PatientMenu.main(new String[]{});
                    frame.dispose();  // Close the current login window
                } else if (user.getUserType().equalsIgnoreCase("Reporter")) {
                    // Launch Reporter Menu in a new window
                    ReporterMenu.main(new String[]{});
                    frame.dispose();  // Close the current login window
                }
            } else {
                JOptionPane.showMessageDialog(frame, "❌ Invalid credentials. Please try again.");
            }
        });
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

