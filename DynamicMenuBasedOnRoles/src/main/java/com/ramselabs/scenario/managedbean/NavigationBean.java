package com.ramselabs.scenario.managedbean;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Ravikant
 *
 */
@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable {
    private static final long serialVersionUID = 1520318172495977648L;
 
    /**
     * Redirect to login page.
     * @return Login page name.
     */
    public String redirectToLogin() {
        return "/views/login.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to login page.
     * @return Login page name.
     */
    public String toLogin() {
        return "/views/login.xhtml?faces-redirect=true";
    }
     
    /**
     * Redirect to home page.
     * @return Home page name.
     */
    public String redirectToHome() {
        return "/secured/home.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to home page.
     * @return Home page name.
     */
    public String toHome() {
        return "/secured/home.xhtml";
    }
	public void keepSessionAlive(){
    	FacesContext fc = FacesContext.getCurrentInstance();
    	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
    	request.getSession();
    }
}

