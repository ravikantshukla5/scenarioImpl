package com.ramselabs.scenario.dao;

import java.util.List;

import com.ramselabs.scenario.entity.Menu;
import com.ramselabs.scenario.entity.Role;
import com.ramselabs.scenario.entity.User;
import com.ramselabs.scenario.model.UserDto;

public interface UserDao {
	Boolean authenticateUser(User user);
	User createUserWithRoles(UserDto userDto);
	User loadUser(User user);
	List<Menu> getUserMenuBasedOnRoles(User user);
	void updateUserTheme(User user);
	List<Role> getAllRoles();
	List<Menu> getMenus();

}
