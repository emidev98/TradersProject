package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Trader;

public class HibernateUtil {
	private static HibernateUtil instance = new HibernateUtil();
	private SessionFactory sessionFactory;
	
	private HibernateUtil() {
		sessionFactory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Trader.class)
			.buildSessionFactory();
	}
	
	public static HibernateUtil getInstance() {
		return instance;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
