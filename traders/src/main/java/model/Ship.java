package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="ship", cascade={CascadeType.ALL})
	private List<ShipOwner> shipOwner;

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
		
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	public List<ShipOwner> getShipOwner() {
		return shipOwner;
	}

	public void setShipOwner(List<ShipOwner> shipOwner) {
		this.shipOwner = shipOwner;
	}

	@Override
	public String toString() {
		return "Ship [id=" + id + ", name=" + name + ", type=" + type + ", capacity=" + capacity + "]";
	}
	
	public int getPrice() {
		switch (type) {
		case "Cargo ship":
			return 100*this.capacity;
		case "Figther":
			return 5000*this.capacity;
		case "Spaceship":
			return 1000*this.capacity;
		case "Battlecruiser":
			return 250*this.capacity;
		case "Battleship":
			return 500*this.capacity;
		case "Spacecruiser":
			return 100*this.capacity;
		default:
			return 300*this.capacity;
		}
	}
	
	public static Ship getShipById(int id) {
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Ship ship = session.get(Ship.class, id);
		session.getTransaction().commit();
		return ship;
	}
	
	/**
	 * Return all available ships from a certain date.
	 * @param date 
	 * @return The list of available ships.
	 */
	@SuppressWarnings("unchecked")
	public static List<Ship> getAvailableShips(Date date){
		//SELECT DISTINCT ships.* FROM ships WHERE ships.Id NOT IN (SELECT ShipOwner.ShipId FROM ShipOwners WHERE shipowners.AdquisitionDate <= "2266-07-05" AND ShipOwners.LostBenefit LIKE 'Destroyed');
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Ship> availableShip = null;
		availableShip = session.createQuery(
					"FROM Ship as ship "
				+ "WHERE ship.id NOT IN ("
				+ "SELECT owner.ship.id FROM ShipOwner as owner WHERE (owner.adquisitionDate <= '"+date+"' AND owner.lostCause LIKE 'Destroyed') OR owner.adquisitionDate = '"+date+"')").getResultList();
		session.getTransaction().commit();
		return availableShip;
	}
	
	
	/**
	 * Filters a list of ships by Type.
	 * @param ships
	 * @param type
	 * @return
	 */
	public static List<Ship> getShipsByType(List<Ship> ships, String type){
		List<Ship> shipsFiltered = new ArrayList<>();
		for(Ship actualShip : ships) {
			if(actualShip.getType().equalsIgnoreCase(type)) {
				shipsFiltered.add(actualShip);
			}
		}
		return shipsFiltered;
	}
}