package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Planet;
import model.SolarSystem;
import model.Trader;
import model.Stay;
import model.SolarSystemsDistance;

public class HibernateUtil {
	private static HibernateUtil instance = new HibernateUtil();
	private SessionFactory sessionFactory;
	
	private HibernateUtil() {
		sessionFactory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Trader.class)
			.addAnnotatedClass(SolarSystem.class)
			.addAnnotatedClass(SolarSystemsDistance.class)
			.addAnnotatedClass(Stay.class)
			.addAnnotatedClass(Planet.class)
			.buildSessionFactory();
	}
	
	public static HibernateUtil getInstance() {
		return instance;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
