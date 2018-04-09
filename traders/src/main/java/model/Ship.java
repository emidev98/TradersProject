package model;

import java.time.LocalDate;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.HibernateUtil;

@ManagedBean
@SessionScoped
@Entity
@Table(name = "Ships")
public class Ship {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Type")
	private String type;

	@Column(name = "Capacity")
	private int capacity;

	@Transient
	private int price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		switch (type) {
		case "Cargo ship":
			setPrice(100);
			break;
		case "Figther":
			setPrice(5000);
			break;
		case "Spaceship":
			setPrice(1000);
			break;
		case "Battlecruiser":
			setPrice(250);
			break;
		case "Battleship":
			setPrice(500);
			break;
		case "Spacecruiser":
			setPrice(100);
			break;
		default:
			setPrice(300);
		}
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	@Override
	public String toString() {
		return "Ship [id=" + id + ", name=" + name + ", type=" + type + ", capacity=" + capacity + "]";
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * Return all available ships from a certain date.
	 * @param date 
	 * @return The list of available ships.
	 */
	@SuppressWarnings("unchecked")
	public static List<Ship> getAvailableShips(LocalDate date){
		//SELECT DISTINCT ships.* FROM ships WHERE ships.Id NOT IN (SELECT shipownergoodss.ShipId FROM shipowners WHERE shipowners.AdquisitionDate <= "2266-07-05");
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Ship> availableShip = null;
		availableShip = session.createQuery(
					"FROM Ship "
				+ "WHERE Id NOT IN ("
				+ "SELECT ShipId FROM shipowners WHERE AdquisitionDate <= '"+date.toString()+"');").getResultList();
		session.getTransaction().commit();
		return availableShip;
		
	}
}
