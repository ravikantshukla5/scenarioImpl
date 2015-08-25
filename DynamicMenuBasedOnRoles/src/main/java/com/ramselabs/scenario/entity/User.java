package com.ramselabs.scenario.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable{
	
	private static final long serialVersionUID = -9138746348151071819L;

	@Id @GeneratedValue
	@Column( name = "id")
	private Integer userId;
	
	@Column( name = "username")
	private String userName;
	
	@Column( name = "password")
	private String password;
	
	@Column( name = "theme")
	private String theme;
	
	@ManyToMany(mappedBy = "roleUsers", cascade = {CascadeType.ALL},fetch=FetchType.EAGER)
	private Collection<Role> userRoles = new ArrayList<Role>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Collection<Role> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Collection<Role> userRoles) {
		this.userRoles = userRoles;
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
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + "]";
	}

}
