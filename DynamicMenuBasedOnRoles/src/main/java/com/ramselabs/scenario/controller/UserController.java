package com.ramselabs.scenario.controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ramselabs.scenario.entity.Menu;
import com.ramselabs.scenario.entity.Role;
import com.ramselabs.scenario.entity.User;
import com.ramselabs.scenario.model.UserDto;
import com.ramselabs.scenario.service.UserService;
import com.ramselabs.scenario.util.HashPasswordUtil;
import com.ramselabs.scenario.util.LoginUtil;
import com.ramselabs.scenario.util.MessageUtil;

@ManagedBean
@ViewScoped
public class UserController extends BaseController {

	private static final long serialVersionUID = 7252313258124589818L;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	private UserDto userDto = new UserDto();

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserRoles() {
		User user = userService.loadUser(LoginUtil.getUser(login));
		List<Role> userRoles = (List<Role>) user.getUserRoles();
		return userRoles.toString();
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public Map<String,String> getRoles(){
		Map<String,String> roleMap = new HashMap<String,String>();
		for(Role role : userService.getAllRoles()){
			roleMap.put(role.getRoleEnum().name(),role.getRoleEnum().name());
		}
		return roleMap;
	}
	public Map<String,String> getMenus(){
		Map<String,String> menuMap = new HashMap<String,String>();
		for(Menu menu : userService.getMenus()){
			menuMap.put(menu.getMenuType().name(),menu.getMenuType().name());
		}
		return menuMap;
	}
	public void createRoles(){
		try {
			userDto.setPassword(HashPasswordUtil.hashPassword(userDto.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		User user = userService.createUserWithRoles(userDto);
		MessageUtil.addInfoMessageWithoutSummary("User :"+user+" created");
	}
}
