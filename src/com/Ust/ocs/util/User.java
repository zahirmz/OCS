package com.Ust.ocs.util;

import com.Ust.ocs.bean.CredentialsBean;

public interface User {
    // Method for logging in a user
    public String login(CredentialsBean credentialsBean);

    // Method for logging out a user
    public boolean logout(String userId);

    // Method for changing a user's password
    public String changePassword(CredentialsBean credentialsBean, String newPassword);
}
