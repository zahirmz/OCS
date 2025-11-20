package com.Ust.ocs.ui;

import com.Ust.ocs.bean.CredentialsBean;
import com.Ust.ocs.dao.PatientDAO;  // Import the DAO to interact with the database
import com.Ust.ocs.util.Authentication;
import java.sql.*;

public class AuthenticationImpl implements Authentication {
    
    @Override
    public boolean authenticate(CredentialsBean user) {
        // Get credentials from the database using PatientDAO
        PatientDAO patientDAO = new PatientDAO();
        CredentialsBean dbUser = patientDAO.getUserCredentials(user.getUserId());
        
        // Check if the user exists in the database and the password matches
        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            user.setLoginStatus(1);  // Set login status to logged in
            user.setUserType(dbUser.getUserType());  // Set the user type based on the database
            return true;  // Successful authentication
        } else {
            return false;  // Invalid credentials
        }
    }

    @Override
    public String authorize(String userId) {
        // You can also authorize the user based on their ID
        PatientDAO patientDAO = new PatientDAO();
        CredentialsBean dbUser = patientDAO.getUserCredentials(userId);

        if (dbUser != null) {
            return dbUser.getUserType();  // Return the user type from the database
        } else {
            return "Unknown";  // If the user is not found
        }
    }

    @Override
    public boolean changeLoginStatus(CredentialsBean user, int loginStatus) {
        if (loginStatus == 0 || loginStatus == 1) {
            user.setLoginStatus(loginStatus);
            return true;
        } else {
            return false;
        }
    }
}

