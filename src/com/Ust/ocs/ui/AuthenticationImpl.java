package com.Ust.ocs.ui;

import com.Ust.ocs.bean.CredentialsBean;
import com.Ust.ocs.util.Authentication;

public class AuthenticationImpl implements Authentication {

    // Hardcoded users (You can later replace this with a database or external file)
    private static final CredentialsBean admin = new CredentialsBean("admin", "admin123", "Administrator", 0);
    private static final CredentialsBean reporter = new CredentialsBean("reporter", "reporter123", "Reporter", 0);
    private static final CredentialsBean patient = new CredentialsBean("patient", "patient123", "Patient", 0);

    @Override
    public boolean authenticate(CredentialsBean user) {
        // Check if the user credentials are valid
        if (user.getUserId().equals(admin.getUserId()) && user.getPassword().equals(admin.getPassword())) {
            user.setLoginStatus(1);  // Logged in successfully
            return true;
        } else if (user.getUserId().equals(reporter.getUserId()) && user.getPassword().equals(reporter.getPassword())) {
            user.setLoginStatus(1);  // Logged in successfully
            return true;
        } else if (user.getUserId().equals(patient.getUserId()) && user.getPassword().equals(patient.getPassword())) {
            user.setLoginStatus(1);  // Logged in successfully
            return true;
        } else {
            return false;  // Invalid credentials
        }
    }

    @Override
    public String authorize(String userId) {
        // Return the role of the user based on userId
        if (userId.equals(admin.getUserId())) {
            return admin.getUserType();
        } else if (userId.equals(reporter.getUserId())) {
            return reporter.getUserType();
        } else if (userId.equals(patient.getUserId())) {
            return patient.getUserType();
        } else {
            return "Unknown"; // User not found
        }
    }

    @Override
    public boolean changeLoginStatus(CredentialsBean user, int loginStatus) {
        // Change the login status (set to 1 for logged in or 0 for logged out)
        if (loginStatus == 0 || loginStatus == 1) {
            user.setLoginStatus(loginStatus);
            return true;
        } else {
            return false;  // Invalid login status
        }
    }
}
