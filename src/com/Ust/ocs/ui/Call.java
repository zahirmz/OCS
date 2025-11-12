package com.Ust.ocs.ui;

import java.util.*;
import com.Ust.ocs.bean.CredentialsBean;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.dao.AdministratorDAO;
import com.Ust.ocs.util.Authentication;

public class Call {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Authentication auth = new AuthenticationImpl();
        System.out.println("\nEnter Username: ");
        String userId = sc.nextLine();
        System.out.println("Enter Password: ");
        String password = sc.nextLine();
        CredentialsBean user = new CredentialsBean(userId, password, "", 0);

        if (auth.authenticate(user)) {
            System.out.println("Login successful! Welcome, " + user.getUserId() + " (" + user.getUserType() + ")");
            System.out.println("Authenticated role: " + user.getUserType());
            if (user.getUserType().equals("Administrator")) {
                showAdminMenu(sc); 
            } else {
                System.out.println("❌ You do not have access to this system.");
            }
        } else {
            System.out.println("❌ Invalid credentials. Please try again.");
        }

        sc.close();
    }

    public static void showAdminMenu(Scanner sc) {
        AdministratorDAO adminDAO = new AdministratorDAO();
        while (true) {  
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. Modify Doctor");
            System.out.println("3. View Doctors");
            System.out.println("4. Remove Doctor");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: 
                    addDoctor(adminDAO, sc);
                    break;
                case 2: 
                    modifyDoctor(adminDAO, sc);
                    break;
                case 3:
                    viewDoctors(adminDAO);  // Simply call the viewDoctors method here
                    break;
                case 4:
                    removeDoctor(adminDAO, sc);
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please select a valid option.");
            }
        }
    }
    private static void modifyDoctor(AdministratorDAO adminDAO, Scanner sc) {
        System.out.print("\nEnter Doctor ID to modify: ");
        String doctorID = sc.nextLine();

        System.out.print("Enter number of fields to modify: ");
        int fieldCount = sc.nextInt();
        sc.nextLine(); // clear newline

        Map<String, String> updatedFields = new HashMap<>();

        System.out.println("\nAvailable fields:");
        System.out.println("1. doctorName");
        System.out.println("2. specialization");
        System.out.println("3. yearsOfExperience");
        System.out.println("4. dateOfBirth");
        System.out.println("5. dateOfJoining");
        System.out.println("6. gender");
        System.out.println("7. qualification");
        System.out.println("8. street");
        System.out.println("9. location");
        System.out.println("10. city");
        System.out.println("11. state");
        System.out.println("12. pincode");
        System.out.println("13. contactNumber");
        System.out.println("14. emailID");

        for (int i = 0; i < fieldCount; i++) {
            System.out.print("\nEnter field number to modify: ");
            int fieldChoice = sc.nextInt();
            sc.nextLine(); // clear newline

            String fieldName = getFieldName(fieldChoice);
            if (fieldName == null) {
                System.out.println("❌ Invalid field choice. Try again.");
                i--;
                continue;
            }

            System.out.print("Enter new value for " + fieldName + ": ");
            String newValue = sc.nextLine();
            updatedFields.put(fieldName, newValue);
        }

        boolean modified = adminDAO.modifyDoctor(doctorID, updatedFields);

        if (modified) {
            System.out.println("\n✅ Doctor details updated successfully!");
        } else {
            System.out.println("\n❌ Doctor ID not found or no changes made!");
        }
    }
    private static String getFieldName(int choice) {
        switch (choice) {
            case 1: return "doctorName";          // doctor_name
            case 2: return "specialization";      // specialization
            case 3: return "yearsOfExperience";   // years_of_experience
            case 4: return "dateOfBirth";         // date_of_birth
            case 5: return "dateOfJoining";       // date_of_joining
            case 6: return "gender";              // gender
            case 7: return "qualification";       // qualification
            case 8: return "street";              // street
            case 9: return "location";            // location
            case 10: return "city";               // city
            case 11: return "state";              // state
            case 12: return "pincode";            // pincode
            case 13: return "contactNumber";      // contact_number
            case 14: return "emailID";            // email_id
            default: return null;                 // if invalid field choice
        }
    }


    private static void addDoctor(AdministratorDAO adminDAO, Scanner sc) {
        System.out.print("\nEnter number of doctors to add: ");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            DoctorBean doctor = new DoctorBean();
            System.out.println("\nEnter details for Doctor " + (i + 1) + ":");
            System.out.print("Enter Doctor ID: ");
            doctor.setDoctorID(sc.nextLine());
            System.out.print("Enter Doctor Name: ");
            doctor.setDoctorName(sc.nextLine());
            System.out.print("Enter Specialization: ");
            doctor.setSpecialization(sc.nextLine());
            System.out.print("Enter Years of Experience: ");
            doctor.setYearsOfExperience(sc.nextInt());
            sc.nextLine();
            System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
            doctor.setDateOfBirth(sc.nextLine());
            System.out.print("Enter Date of Joining (YYYY-MM-DD): ");
            doctor.setDateOfJoining(sc.nextLine());
            System.out.print("Enter Gender: ");
            doctor.setGender(sc.nextLine());
            System.out.print("Enter Qualification: ");
            doctor.setQualification(sc.nextLine());
            System.out.print("Enter Street Address: ");
            doctor.setStreet(sc.nextLine());
            System.out.print("Enter Location: ");
            doctor.setLocation(sc.nextLine());
            System.out.print("Enter City: ");
            doctor.setCity(sc.nextLine());
            System.out.print("Enter State: ");
            doctor.setState(sc.nextLine());
            System.out.print("Enter Pincode: ");
            doctor.setPincode(sc.nextLine());
            System.out.print("Enter Contact Number: ");
            doctor.setContactNumber(sc.nextLine());
            System.out.print("Enter Email ID: ");
            doctor.setEmailID(sc.nextLine());

            String result = adminDAO.addDoctor(doctor);
            System.out.println(result);
        }
    }
    
    private static void viewDoctors(AdministratorDAO adminDAO) {
        System.out.println("\n--- List of All Doctors ---");
        ArrayList<DoctorBean> doctors = adminDAO.viewAllDoctors();
        for (DoctorBean doctor : doctors) {
            System.out.println(doctor); // This will call the overridden toString() method
        }
    }

    private static void removeDoctor(AdministratorDAO adminDAO, Scanner sc) {
        System.out.print("\nEnter Doctor ID to remove: ");
        String removeID = sc.nextLine();

        int result = adminDAO.removeDoctor(removeID);
        if (result == 1) {
            System.out.println("✅ Doctor removed successfully!");
        } else {
            System.out.println("❌ Doctor ID not found!");
        }
    }
}
