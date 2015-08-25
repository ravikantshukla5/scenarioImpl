package com.ramselabs.scenario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ramselabs.scenario.util.SubMenuType;

@Entity
@Table(name = "sub_menus")
public class SubMenu implements Serializable{
	private static final long serialVersionUID = 6588650840918309257L;

	@Column( name = "id")
	@Id @GeneratedValue
	private Integer subMenuId;
	
	@Column( name = "action_url")
	private String actionUrl;
	
	@Column( name = "sub_menu_name",length = 20)
	@Enumerated(value = EnumType.STRING)
	private SubMenuType sunMenuType;
	
	@ManyToOne
	@JoinColumn( name = "menu_id")
	private Menu menu;

	public Integer getSubMenuId() {
		return subMenuId;
	}

	public void setSubMenuId(Integer subMenuId) {
		this.subMenuId = subMenuId;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public SubMenuType getSunMenuType() {
		return sunMenuType;
	}

	public void setSunMenuType(SubMenuType sunMenuType) {
		this.sunMenuType = sunMenuType;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((subMenuId == null) ? 0 : subMenuId.hashCode());
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
		SubMenu other = (SubMenu) obj;
		if (subMenuId == null) {
			if (other.subMenuId != null)
				return false;
		} else if (!subMenuId.equals(other.subMenuId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubMenu [subMenuId=" + subMenuId + ", actionUrl=" + actionUrl
				+ ", sunMenuType=" + sunMenuType + "]";
	}

}
