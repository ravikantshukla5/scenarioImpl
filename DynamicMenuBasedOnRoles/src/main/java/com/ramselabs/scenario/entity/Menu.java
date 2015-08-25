package com.ramselabs.scenario.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ramselabs.scenario.util.MenuType;

@Entity
@Table( name = "menus")
public class Menu implements Serializable{
	
	private static final long serialVersionUID = -4762247301574377767L;

	@Column(name = "id")
	@Id @GeneratedValue
	private Integer menuId;
	
	@Column( name = "menu_action")
	private String menuAction;
	
	@Column( name = "menu_name" , length = 20, nullable = false)
	@Enumerated(value = EnumType.STRING)
	private MenuType menuType;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@OneToMany(mappedBy="menu",cascade = {CascadeType.ALL},fetch=FetchType.EAGER)
	private Collection<SubMenu> subMenus= new ArrayList<SubMenu>();

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuAction() {
		return menuAction;
	}

	public void setMenuAction(String menuAction) {
		this.menuAction = menuAction;
	}

	public MenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Collection<SubMenu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(Collection<SubMenu> subMenus) {
		this.subMenus = subMenus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
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
		Menu other = (Menu) obj;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Menu [menuType=" + menuType + "]";
	}

}
