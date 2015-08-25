package com.ramselabs.scenario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramselabs.scenario.dao.UserDao;
import com.ramselabs.scenario.entity.Menu;
import com.ramselabs.scenario.entity.Role;
import com.ramselabs.scenario.entity.User;
import com.ramselabs.scenario.model.UserDto;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public Boolean authenticateUser(User user) {
		return userDao.authenticateUser(user);
	}

	@Override
	@Transactional(readOnly = false)
	public User createUserWithRoles(UserDto userDto) {
		return userDao.createUserWithRoles(userDto);
	}

	@Override
	public User loadUser(User user) {
		return userDao.loadUser(user);
	}

	@Override
	public List<Menu> getUserMenuBasedOnRoles(User user) {
		return userDao.getUserMenuBasedOnRoles(user);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateUserThemePreferences(User user) {
        userDao.updateUserTheme(user);		
	}

	@Override
	public List<Role> getAllRoles() {
		return userDao.getAllRoles();
	}

	@Override
	public List<Menu> getMenus() {
		return userDao.getMenus();
	}

}
