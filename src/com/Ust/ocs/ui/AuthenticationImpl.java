package com.Ust.ocs.ui;
import com.Ust.ocs.bean.CredentialsBean;
import com.Ust.ocs.util.Authentication;

public class AuthenticationImpl implements Authentication {
    private static final CredentialsBean admin = new CredentialsBean("zahir", "zahir123", "Administrator", 0);
    private static final CredentialsBean reporter = new CredentialsBean("reporter", "reporter123", "Reporter", 0);
    private static final CredentialsBean patient = new CredentialsBean("patient", "patient123", "Patient", 0);
    @Override
    public boolean authenticate(CredentialsBean user) {
        if (user.getUserId().equals(admin.getUserId()) && user.getPassword().equals(admin.getPassword())) {
            user.setLoginStatus(1);  
            user.setUserType(admin.getUserType());  
            return true;
        } else if (user.getUserId().equals(reporter.getUserId()) && user.getPassword().equals(reporter.getPassword())) {
            user.setLoginStatus(1);  
            user.setUserType(reporter.getUserType());  
            return true;
        } else if (user.getUserId().equals(patient.getUserId()) && user.getPassword().equals(patient.getPassword())) {
            user.setLoginStatus(1);  
            user.setUserType(patient.getUserType()); 
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String authorize(String userId) { 
        if (userId.equals(admin.getUserId())) {
            return admin.getUserType();
        } else if (userId.equals(reporter.getUserId())) {
            return reporter.getUserType();
        } else if (userId.equals(patient.getUserId())) {
            return patient.getUserType();
        } else {
            return "Unknown"; 
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
