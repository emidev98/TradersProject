package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import sakila_online.Customer;

public class HibernateUtil {
	private static HibernateUtil instance = new HibernateUtil();
	private SessionFactory sessionFactory;
	
	private HibernateUtil() {
		sessionFactory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Customer.class)
			.buildSessionFactory();
	}
	
	public static HibernateUtil getInstance() {
		return instance;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
