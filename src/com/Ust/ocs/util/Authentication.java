package com.Ust.ocs.util;

import com.Ust.ocs.bean.CredentialsBean;

public interface Authentication {
	public boolean authenticate(CredentialsBean user) ;
	public String authorize(String userId);
	public boolean changeLoginStatus(CredentialsBean user, int loginStatus);
	
}
