package com.ramselabs.scenario.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import com.ramselabs.scenario.managedbean.LoginBean;

public abstract class BaseController implements Serializable{
	private static final long serialVersionUID = 206650521640624598L;
	@ManagedProperty(value = "#{loginBean}")
	protected LoginBean login;

	@PostConstruct
	public void checkLoginRedirect() {
		// redirect to login if user tries to access the page before login
		if (login.getUsername() == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("views/login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public LoginBean getLogin() {
		return login;
	}

}
