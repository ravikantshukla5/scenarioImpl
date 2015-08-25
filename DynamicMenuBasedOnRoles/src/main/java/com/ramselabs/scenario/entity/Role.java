package com.ramselabs.scenario.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ramselabs.scenario.util.RoleEnum;

@Entity
@Table(name="roles")
public class Role implements Serializable{
	
	private static final long serialVersionUID = 6757683568984042770L;

	@Id @GeneratedValue
	@Column(name="id")
	private int roleId;
	
	@ManyToMany
	@JoinTable(name="user_role",joinColumns=@JoinColumn(name="role_id"),inverseJoinColumns=@JoinColumn(name="user_id"))
	private Collection<User> roleUsers=new ArrayList<User>();
	
	@Column( name = "role_name", length = 20, nullable = false)
	@Enumerated(value = EnumType.STRING)
	private RoleEnum roleEnum;
	
	@OneToMany(mappedBy = "role",cascade = {CascadeType.ALL})
	private Collection<Menu> menus = new ArrayList<Menu>();
	
	public RoleEnum getRoleEnum() {
		return roleEnum;
	}

	public void setRoleEnum(RoleEnum roleEnum) {
		this.roleEnum = roleEnum;
	}
	public Collection<User> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(Collection<User> roleUsers) {
		this.roleUsers = roleUsers;
	}

	public int getRoleId() {
		return roleId;
	}
    
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public Collection<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Collection<Menu> menus) {
		this.menus = menus;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roleId;
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
		Role other = (Role) obj;
		if (roleId != other.roleId)
			return false;
		return true;
	}

	public String toString(){
		return roleEnum.name();
	}
}
