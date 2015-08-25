package com.ramselabs.scenario.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import com.ramselabs.scenario.entity.Menu;
import com.ramselabs.scenario.entity.Role;
import com.ramselabs.scenario.entity.User;

public class HIbernateUtil {
	private static SessionFactory sessionFactory = null;
	static {
		Configuration config = new Configuration();
		config.configure();
		ServiceRegistryBuilder ssrb = new ServiceRegistryBuilder()
				.applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(ssrb.buildServiceRegistry());
	}

	private static SessionFactory getSessionFactory() {
		if (sessionFactory != null)
			return sessionFactory;
		else {
			Configuration config = new Configuration();
			config.configure();
			ServiceRegistryBuilder ssrb = new ServiceRegistryBuilder()
					.applySettings(config.getProperties());
			sessionFactory = config.buildSessionFactory(ssrb.buildServiceRegistry());
			return sessionFactory;
		}
	}

	public static Boolean authenticateUser(User user) {
		Query query = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			query = session.createQuery("from User where username='"
					+ user.getUserName() + "' and password='"
					+ user.getPassword() + "'");
			return (query.list().size() > 0);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static User createUserWithRoles(User user) {
		try {
			Session session = getSessionFactory().openSession();
			session.beginTransaction();
			session.persist(user);
			session.getTransaction().commit();
			session.flush();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return user;
	}

	public static User loadUser(User user) {
		Query query = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			query = session.createQuery("from User where username='"
					+ user.getUserName() + "' and password='"
					+ user.getPassword() + "'");
			User userLoad = (User) query.list().get(0);
			session.flush();
			session.close();
			return userLoad;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Menu> getUserMenuBasedOnRoles(User user) {
		Session session = null;
		List<Menu> userMenus = new ArrayList<Menu>();
		try {
			session = getSessionFactory().openSession();
			User persistUser = (User) session.get(User.class, user.getUserId());
			Collection<Role> roles = persistUser.getUserRoles();
			for (Role role : roles) {
				if (role != null) {
					for (Menu menu : role.getMenus()) {
                         userMenus.add(menu);
					}
				}
			}
			session.flush();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return userMenus;
	}

}
