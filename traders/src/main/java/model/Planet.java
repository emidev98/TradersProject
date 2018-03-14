package model;

import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.HibernateUtil;

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
	
	@Column(name = "SystemId")
	private int systemId;
	
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

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
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
				+ ", SystemId=" + systemId + ", Average Distance=" + averageDistance + "]";
	}
	
	public void savePlanet() throws SQLException{
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(this);
		session.getTransaction().commit();
	}
}
