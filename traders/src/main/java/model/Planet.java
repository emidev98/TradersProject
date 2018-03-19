package model;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.HibernateUtil;

@ManagedBean
@SessionScoped
@Entity
@Table(name = "Planets")
public class Planet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Code")
	private String code;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Position")
	private int position;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="SystemId")
	private SolarSystem system;
	
	@Column(name = "AverageDistance")
	private float averageDistance;

	public Planet() {
		;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public SolarSystem getSystem() {
		return system;
	}

	public void setSystem(SolarSystem solarSystem) {
		this.system = solarSystem;
	}

	public float getAverageDistance() {
		return averageDistance;
	}

	public void setAverageDistance(float averageDistance) {
		this.averageDistance = averageDistance;
	}
	
	@Override
	public String toString() {
		return "Planet [id=" + id + ", Code=" + code + ", Name=" + name + ", Position=" + position
			 + ", Average Distance=" + averageDistance + "]";
	}
	
	public void savePlanet() throws SQLException{
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(this);
		session.getTransaction().commit();
	}
	
	public static List<Planet> getAllPlanets(){
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Planet> planets = session.createQuery("from Planets").getResultList();
		session.getTransaction().commit();
		return planets;
	}
}
