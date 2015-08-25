package com.ramselabs.scenario.controller;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import com.ramselabs.scenario.entity.Menu;
import com.ramselabs.scenario.entity.User;
import com.ramselabs.scenario.managedbean.NavigationBean;
import com.ramselabs.scenario.model.AvailableThemes;
import com.ramselabs.scenario.service.UserService;
import com.ramselabs.scenario.util.LoginUtil;
import com.ramselabs.scenario.util.MessageUtil;

/**
 * @author Ravikant
 * 
 */
@ManagedBean
@SessionScoped
public class LoginController extends BaseController {

	private static final long serialVersionUID = 2001099389416935790L;

	private boolean loggedIn;

	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	@ManagedProperty(value = "#{userSettingsController}")
	private UserSettingsController userSettingController;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	private List<Menu> userMenu;

	public String verifyLogin() {
		if (login.getUsername() == null || login.getPassword() == null) {
			return null;
		}
		if (!userService.authenticateUser(LoginUtil.getUser(login))) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(
					"Invalid username or password"));
			return null;

		}

		User currentUser = userService.loadUser(LoginUtil.getUser(login));
		userSettingController.setCurrentTheme(AvailableThemes.instance()
				.getTheme(currentUser.getTheme()));

		if (currentUser.getUserRoles() != null) {
			loggedIn = true;
			userMenu = userService.getUserMenuBasedOnRoles(currentUser);
			return navigationBean.redirectToHome();
		} else {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(
					"No role assigned to you. Kindly contact Administrator."));
			return null;
		}
	}

	public String doLogout() {
		// Set the paremeter indicating that user is logged in to false
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		loggedIn = false;
		return navigationBean.toLogin();
	}

	public void checkForActiveSession() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fc
				.getExternalContext().getRequest();
		if (request.getSession() == null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage("SessioExpired"));
		}
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setUserSettingController(
			UserSettingsController userSettingController) {
		this.userSettingController = userSettingController;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public List<Menu> getUserMenu() {
		return userMenu;
	}

	public void setUserMenu(List<Menu> userMenu) {
		this.userMenu = userMenu;
	}

	public void doMenuAction() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext()
				.getRequestParameterMap();
		String commandName = map.get("commandName");
		if (commandName.equalsIgnoreCase("CREATE")) {
			RequestContext.getCurrentInstance().execute(
					"dlgForCreateRoles.show();");
		}
		else{
			MessageUtil.addInfoMessageWithoutSummary("Submenu "+commandName+": clicked");
		}
	}
}
