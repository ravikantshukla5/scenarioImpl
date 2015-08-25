package com.ramselabs.scenario.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ramselabs.scenario.entity.User;
import com.ramselabs.scenario.model.AvailableThemes;
import com.ramselabs.scenario.model.Theme;
import com.ramselabs.scenario.model.UserPreferences;
import com.ramselabs.scenario.service.UserService;
import com.ramselabs.scenario.util.LoginUtil;
import com.ramselabs.scenario.util.MessageUtil;


/**
 * @author Ravikant
 *
 */
@ManagedBean
@SessionScoped
public class UserSettingsController extends BaseController implements Serializable {
	
	private static final long serialVersionUID = -5684404650135075995L;
	@ManagedProperty(value="#{userService}")
	private UserService userService;
	private Map<String, String> themes;
	private String theme;
	private UserPreferences userPreferences = new UserPreferences();
	private List<Theme> availableThemes;
	private Theme currentTheme;
	private List<Theme> advancedThemes;
	public List<Theme> getAdvancedThemes() {
		return advancedThemes;
	}

	public void setAdvancedThemes(List<Theme> advancedThemes) {
		this.advancedThemes = advancedThemes;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Map<String, String> getThemes() {
		return themes;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
		setCurrentTheme(AvailableThemes.instance().getTheme(theme));
	}

	public void saveTheme() {
		userPreferences.setTheme(theme);
		User user=LoginUtil.getUser(login);
		user.setTheme(theme);
		userService.updateUserThemePreferences(user);
		MessageUtil.addInfoMessageWithoutSummary("Theme is updated successfully");
	}

	@PostConstruct
	public void init() {
		advancedThemes=new ArrayList<Theme>();
		advancedThemes.addAll(AvailableThemes.instance().getThemes());
		theme = userPreferences.getTheme();
	}

	// Stateful Switcher (Full page refresh)

	public UserSettingsController() {
		currentTheme = AvailableThemes.instance().getTheme("home");
		availableThemes = AvailableThemes.instance().getThemes();
	}
    
	public List<Theme> getAvailableThemes() {
		return availableThemes;
	}

	public void setAvailableThemes(List<Theme> availableThemes) {
		this.availableThemes = availableThemes;
	}

	public Theme getCurrentTheme() {
		return currentTheme;
	}

	public void setCurrentTheme(Theme currentTheme) {
		this.currentTheme = currentTheme;
	}
}