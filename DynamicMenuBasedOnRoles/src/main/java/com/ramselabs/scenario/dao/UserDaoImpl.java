package com.ramselabs.scenario.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ramselabs.scenario.entity.Menu;
import com.ramselabs.scenario.entity.Role;
import com.ramselabs.scenario.entity.SubMenu;
import com.ramselabs.scenario.entity.User;
import com.ramselabs.scenario.model.UserDto;
import com.ramselabs.scenario.util.MenuType;
import com.ramselabs.scenario.util.RoleEnum;
import com.ramselabs.scenario.util.SubMenuType;
@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Boolean authenticateUser(User user) {
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from User where username='"
					+ user.getUserName() + "' and password='"
					+ user.getPassword() + "'");
			return (query.list().size() > 0);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public User createUserWithRoles(UserDto userDto) {
		Collection<Role> roles = new ArrayList<Role>();
		Collection<Menu> menus = new ArrayList<Menu>();
		User user = new User();
		user.setTheme("home");
		user.setUserName(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		for(String roleName : userDto.getUserRoles()){
			Role role = new Role();
			role.setRoleEnum(RoleEnum.valueOf(roleName));
			role.getRoleUsers().add(user);
			roles.add(role);
		}
		for(String menuName : userDto.getMenus()){
			Menu menu = new Menu();
			menu.setMenuAction("www.google.com");
			menu.setMenuType(MenuType.valueOf(menuName));
			SubMenu subMenu = new SubMenu();
			subMenu.setActionUrl("www.google.com");
			subMenu.setSunMenuType(SubMenuType.CONTENT);
			subMenu.setMenu(menu);
			for(Role role : roles){
				menu.setRole(role);
			}
			menu.getSubMenus().add(subMenu);
			menus.add(menu);
		}
		for(Role role : roles){
			role.setMenus(menus);
		}
		user.setUserRoles(roles);
		sessionFactory.getCurrentSession().persist(user);
		return user;
	}

	@Override
	public User loadUser(User user) {
		User userLoad = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from User where username='"
					+ user.getUserName() + "' and password='"
					+ user.getPassword() + "'");
			userLoad = (User) query.list().get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return userLoad;
	}

	@Override
	public List<Menu> getUserMenuBasedOnRoles(User user) {
		List<Menu> userMenus = new ArrayList<Menu>();
		try {
			User persistUser = (User) sessionFactory.getCurrentSession().get(User.class, user.getUserId());
			Collection<Role> roles = persistUser.getUserRoles();
			for (Role role : roles) {
				if (role != null) {
					for (Menu menu : role.getMenus()) {
                         userMenus.add(menu);
					}
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return userMenus;
	}

	@Override
	public void updateUserTheme(User user) {
		User usr = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from User where username='"
					+ user.getUserName() + "' and password='"
					+ user.getPassword() + "'");
            usr = (User)query.list().get(0);
            usr.setTheme(user.getTheme());
            sessionFactory.getCurrentSession().update(usr);
		} catch (HibernateException e) {
			e.printStackTrace();
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRoles() {
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from Role");
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return (List<Role>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getMenus() {
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from Menu");
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return (List<Menu>)query.list();
	}

}
