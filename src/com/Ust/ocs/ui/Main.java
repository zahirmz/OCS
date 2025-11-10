//package com.Ust.ocs.ui;
//
//import java.util.*;
//import com.Ust.ocs.bean.CredentialsBean;
//import com.Ust.ocs.util.Authentication;
//import com.Ust.ocs.ui.AuthenticationImpl;
//import com.Ust.ocs.dao.AdministratorDAO;
//import com.Ust.ocs.bean.DoctorBean;
//
//public class Main {
//	//database connection
//	
//	
//	class 
	
	
//	
//	
//	
//    public static void main(String[] args) {
////    	System.out.println("  /$$$$$$                                      /$$$$$$$            /$$       /$$                           /$$   /$$                     /$$   /$$     /$$                            /$$$$$$  /$$$$$$$  /$$   /$$\r\n"
////    			+ " /$$__  $$                                    | $$__  $$          |__/      | $$                          | $$  | $$                    | $$  | $$    | $$                           /$$__  $$| $$__  $$| $$  | $$\r\n"
////    			+ "| $$  \\__/  /$$$$$$   /$$$$$$   /$$$$$$       | $$  \\ $$  /$$$$$$  /$$  /$$$$$$$  /$$$$$$   /$$$$$$       | $$  | $$  /$$$$$$   /$$$$$$ | $$ /$$$$$$  | $$$$$$$                     | $$  \\__/| $$  \\ $$| $$  | $$\r\n"
////    			+ "| $$       |____  $$ /$$__  $$ /$$__  $$      | $$$$$$$  /$$__  $$| $$ /$$__  $$ /$$__  $$ /$$__  $$      | $$$$$$$$ /$$__  $$ |____  $$| $$|_  $$_/  | $$__  $$       /$$$$$$      | $$      | $$$$$$$ | $$$$$$$$\r\n"
////    			+ "| $$        /$$$$$$$| $$  \\__/| $$$$$$$$      | $$__  $$| $$  \\__/| $$| $$  | $$| $$  \\ $$| $$$$$$$$      | $$__  $$| $$$$$$$$  /$$$$$$$| $$  | $$    | $$  \\ $$      |______/      | $$      | $$__  $$| $$__  $$\r\n"
////    			+ "| $$    $$ /$$__  $$| $$      | $$_____/      | $$  \\ $$| $$      | $$| $$  | $$| $$  | $$| $$_____/      | $$  | $$| $$_____/ /$$__  $$| $$  | $$ /$$| $$  | $$                    | $$    $$| $$  \\ $$| $$  | $$\r\n"
////    			+ "|  $$$$$$/|  $$$$$$$| $$      |  $$$$$$$      | $$$$$$$/| $$      | $$|  $$$$$$$|  $$$$$$$|  $$$$$$$      | $$  | $$|  $$$$$$$|  $$$$$$$| $$  |  $$$$/| $$  | $$                    |  $$$$$$/| $$$$$$$/| $$  | $$\r\n"
////    			+ " \\______/  \\_______/|__/       \\_______/      |_______/ |__/      |__/ \\_______/ \\____  $$ \\_______/      |__/  |__/ \\_______/ \\_______/|__/   \\___/  |__/  |__/                     \\______/ |_______/ |__/  |__/\r\n"
////    			+ "                                                                                 /$$  \\ $$                                                                                                                        \r\n"
////    			+ "                                                                                |  $$$$$$/                                                                                                                        \r\n"
////    			+ "                                                                                 \\______/                                                                                                                         ");
//        Scanner sc=new Scanner(System.in);
//        
//        
//        System.out.println("   ___                ___     _    _            _  _          _ _   _             ___ ___ _  _ \r\n"
//        		+ "  / __|__ _ _ _ ___  | _ )_ _(_)__| |__ _ ___  | || |___ __ _| | |_| |_    ___   / __| _ ) || |\r\n"
//        		+ " | (__/ _` | '_/ -_) | _ \\ '_| / _` / _` / -_) | __ / -_) _` | |  _| ' \\  |___| | (__| _ \\ __ |\r\n"
//        		+ "  \\___\\__,_|_| \\___| |___/_| |_\\__,_\\__, \\___| |_||_\\___\\__,_|_|\\__|_||_|        \\___|___/_||_|\r\n"
//        		+ "                                    |___/                                                      ");
//
//        
//        Authentication auth = new AuthenticationImpl();
//        System.out.println("\nEnter Username: ");
//        String userId = sc.nextLine();
//        System.out.println("Enter Password: ");
//        String password = sc.nextLine();
//        CredentialsBean user = new CredentialsBean(userId, password, "", 0);
//
//       
//        if (auth.authenticate(user)) {
//           
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
//                
//                case 2: 
//                    modifyDoctor(adminDAO, sc);
//                    break;
//                
//                case 3: 
//                    viewDoctors(adminDAO);
//                    break;
//                
//                case 4:
//                    removeDoctor(adminDAO, sc);
//                    break;
//                
//                case 5:
//                    System.out.println("Exiting... Goodbye!");
//                    return; 
//                
//                default:
//                    System.out.println("❌ Invalid choice. Please select a valid option.");
//            }
//        }
//    }
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
//
//    private static void modifyDoctor(AdministratorDAO adminDAO, Scanner sc) {
//        System.out.print("\nEnter Doctor ID to modify: ");
//        String doctorID = sc.nextLine();
//        DoctorBean updatedDoctor = new DoctorBean();
//        updatedDoctor.setDoctorID(doctorID);
//        System.out.print("Enter new Doctor Name: ");
//        updatedDoctor.setDoctorName(sc.nextLine());
//        System.out.print("Enter new Specialization: ");
//        updatedDoctor.setSpecialization(sc.nextLine());
//        System.out.print("Enter new Years of Experience: ");
//        updatedDoctor.setYearsOfExperience(sc.nextInt());
//        sc.nextLine(); 
//        System.out.print("Enter new Date of Birth (YYYY-MM-DD): ");
//        updatedDoctor.setDateOfBirth(sc.nextLine());
//        System.out.print("Enter new Date of Joining (YYYY-MM-DD): ");
//        updatedDoctor.setDateOfJoining(sc.nextLine());
//        System.out.print("Enter new Gender: ");
//        updatedDoctor.setGender(sc.nextLine());
//        System.out.print("Enter new Qualification: ");
//        updatedDoctor.setQualification(sc.nextLine());
//        System.out.print("Enter new Street Address: ");
//        updatedDoctor.setStreet(sc.nextLine());
//        System.out.print("Enter new Location: ");
//        updatedDoctor.setLocation(sc.nextLine());
//        System.out.print("Enter new City: ");
//        updatedDoctor.setCity(sc.nextLine());
//        System.out.print("Enter new State: ");
//        updatedDoctor.setState(sc.nextLine());
//        System.out.print("Enter new Pincode: ");
//        updatedDoctor.setPincode(sc.nextLine());
//        System.out.print("Enter new Contact Number: ");
//        updatedDoctor.setContactNumber(sc.nextLine());
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
//    }
//
//
//    private static void viewDoctors(AdministratorDAO adminDAO) {
//        System.out.println("\n--- List of All Doctors ---\n");
//        ArrayList<DoctorBean> doctors = adminDAO.viewAllDoctors();
//        for (DoctorBean doc : doctors) {
//            System.out.println("Doctor ID: " + doc.getDoctorID() + " \nName: " + doc.getDoctorName() +
//                               "\nSpecialization: " + doc.getSpecialization() +
//                               " \nDate of Birth: " + doc.getDateOfBirth() + 
//                               " \nDate of Joining: " + doc.getDateOfJoining() + 
//                               " \nGender: " + doc.getGender() + 
//                               " \nQualification: " + doc.getQualification() + 
//                               " \nYears of Experience: " + doc.getYearsOfExperience() +
//                               " \nContact Number: " + doc.getContactNumber() +
//                               " \nEmail ID: " + doc.getEmailID() + "\n");
//        }
//    }
//
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
import java.sql.*;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ustglobal","root","pass@word1");
	System.out.println("Connected Successfully");
	PreparedStatement ps =con.prepareStatement("Insert into employee values(?,?,?)");
	ps.setInt(1, 101);
	ps.setString(2, "Rahul");
	ps.setInt(3, 30000);
	int i=ps.executeUpdate();
	System.out.println(i+" record inserted...");
	
	}
}


