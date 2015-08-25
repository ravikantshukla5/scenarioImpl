package com.ramselabs.scenario.util;

import java.security.NoSuchAlgorithmException;

import com.ramselabs.scenario.entity.User;
import com.ramselabs.scenario.managedbean.LoginBean;

public class LoginUtil {

	public static User getUser(LoginBean login) {
		User user = null;
		if(login != null){
			user = new User();
			user.setUserName(login.getUsername());
			try {
				user.setPassword(HashPasswordUtil.hashPassword(login.getPassword()));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

}
