package com.ramselabs.scenario.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 7734424792873173017L;
	private Integer userId;
	private String username;
	private String password;
	private List<String> userRoles = new ArrayList<String>();
	private List<String> menus = new ArrayList<String>();
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public List<String> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}
	public List<String> getMenus() {
		return menus;
	}
	public void setMenus(List<String> menus) {
		this.menus = menus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", username=" + username + "]";
	}

}
