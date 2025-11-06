


package com.Ust.ocs.ui;

import java.util.*;
import com.Ust.ocs.bean.DoctorBean;
import com.Ust.ocs.dao.AdministratorDAO;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
                    viewDoctors(adminDAO);
                    break;
                
                case 4:
                    removeDoctor(adminDAO, sc);
                    break;
                
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return; 
                
                default:
                    System.out.println("❌ Invalid choice. Please select a valid option.");
            }
        }
    }

    // Method to add a doctor******************************************************************************
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

            System.out.print("Enter Contact Number: ");
            doctor.setContactNumber(sc.nextLine());

            System.out.print("Enter Email ID: ");
            doctor.setEmailID(sc.nextLine());

            String result = adminDAO.addDoctor(doctor);
            System.out.println(result);
        }
    }

    // Method to modify a doctor******************************************************************************
    private static void modifyDoctor(AdministratorDAO adminDAO, Scanner sc) {
        System.out.print("\nEnter Doctor ID to modify: ");
        String doctorID = sc.nextLine();

        DoctorBean updatedDoctor = new DoctorBean();
        updatedDoctor.setDoctorID(doctorID);

        System.out.print("Enter new Doctor Name: ");
        updatedDoctor.setDoctorName(sc.nextLine());

        System.out.print("Enter new Specialization: ");
        updatedDoctor.setSpecialization(sc.nextLine());

        System.out.print("Enter new Years of Experience: ");
        updatedDoctor.setYearsOfExperience(sc.nextInt());
        sc.nextLine();

        System.out.print("Enter new Contact Number: ");
        updatedDoctor.setContactNumber(sc.nextLine());

        System.out.print("Enter new Email ID: ");
        updatedDoctor.setEmailID(sc.nextLine());

        boolean modified = adminDAO.modifyDoctor(updatedDoctor);

        if (modified) {
            System.out.println("\n✅ Doctor updated successfully!");
        } else {
            System.out.println("\n❌ Doctor ID not found!");
        }
    }

    // Method to view all doctors*********************************************************************************************************************
    private static void viewDoctors(AdministratorDAO adminDAO) {
        System.out.println("\n--- List of All Doctors ---");
        ArrayList<DoctorBean> doctors = adminDAO.viewAllDoctors();
        for (DoctorBean doc : doctors) {
            System.out.println("Doctor ID: " + doc.getDoctorID() + ", Name: " + doc.getDoctorName() +
                               ", Specialization: " + doc.getSpecialization());
        }
    }

    // Method to remove a doctor******************************************************************************
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



//package com.Ust.ocs.ui;
//
//import java.util.*;
//import com.Ust.ocs.bean.DoctorBean;
//import com.Ust.ocs.dao.AdministratorDAO;
//
//public class Main {
//    public static void main(String[] args) {
//        
//        Scanner sc = new Scanner(System.in);
//        AdministratorDAO adminDAO = new AdministratorDAO(); // Create once
//
//        System.out.print("Enter number of doctors to add: ");
//        int n = sc.nextInt();
//        sc.nextLine(); // To consume leftover newline
//
//        // Loop to add doctors
//        for (int i = 0; i < n; i++) {
//            DoctorBean doctor = new DoctorBean();
//            System.out.println("\nEnter details for Doctor " + (i + 1) + ":");
//
//            System.out.print("Enter Doctor ID: ");
//            doctor.setDoctorID(sc.nextLine());
//
//            System.out.print("Enter Doctor Name: ");
//            doctor.setDoctorName(sc.nextLine());
//
//            System.out.print("Enter Specialization: ");
//            doctor.setSpecialization(sc.nextLine());
//
//            System.out.print("Enter Years of Experience: ");
//            doctor.setYearsOfExperience(sc.nextInt());
//            sc.nextLine(); // Consume newline
//
//            System.out.print("Enter Contact Number: ");
//            doctor.setContactNumber(sc.nextLine());
//
//            System.out.print("Enter Email ID: ");
//            doctor.setEmailID(sc.nextLine());
//
//            // Add doctor
//            String result = adminDAO.addDoctor(doctor);
//            System.out.println(result);
//        }
//
//        // Display all doctors
//        System.out.println("\n--- List of All Doctors ---");
//        ArrayList<DoctorBean> doctors = adminDAO.viewAllDoctors();
//        for (DoctorBean doc : doctors) {
//            System.out.println("Doctor ID: " + doc.getDoctorID() + ", Name: " + doc.getDoctorName() +
//                               ", Specialization: " + doc.getSpecialization());
//        }

//        // Modify existing doctor
//        System.out.print("\nEnter Doctor ID to modify: ");
//        String doctorID = sc.nextLine();
//
//        DoctorBean updatedDoctor = new DoctorBean();
//        updatedDoctor.setDoctorID(doctorID);
//
//        System.out.print("Enter new Doctor Name: ");
//        updatedDoctor.setDoctorName(sc.nextLine());
//
//        System.out.print("Enter new Specialization: ");
//        updatedDoctor.setSpecialization(sc.nextLine());
//
//        System.out.print("Enter new Years of Experience: ");
//        updatedDoctor.setYearsOfExperience(sc.nextInt());
//        sc.nextLine(); // Consume newline
//
//        System.out.print("Enter new Contact Number: ");
//        updatedDoctor.setContactNumber(sc.nextLine());
//
//        System.out.print("Enter new Email ID: ");
//        updatedDoctor.setEmailID(sc.nextLine());
//
//        boolean modified = adminDAO.modifyDoctor(updatedDoctor);
//
//        if (modified) {
//            System.out.println("\n✅ Doctor updated successfully!");
//        } else {
//            System.out.println("\n❌ Doctor ID not found!");
//        }
        
        //doctor to remove
//        System.out.print("\nEnter Doctor ID to remove: ");
//        String removeID = sc.nextLine();
//
//        int result = adminDAO.removeDoctor(removeID);
//        if (result == 1) {
//            System.out.println("✅ Doctor removed successfully!");
//        } else {
//            System.out.println("❌ Doctor ID not found!");
//        }
//
//        sc.close();
//    }
//}
