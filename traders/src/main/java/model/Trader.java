package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.HibernateUtil;

@ManagedBean
@SessionScoped
@Entity
@Table(name = "Traders")
public class Trader {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Nickname")
	private String nickname;
	
	@Column(name = "Firstname")
	private String firstName;
	
	@Column(name = "Lastname")
	private String lastName;
	
	@Column(name = "DefunctDate")
	private Date defunctDate;
	
	@Transient
	private Date startDate;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="trader", cascade={CascadeType.ALL})
	private List<Stay> stays;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="trader", cascade={CascadeType.ALL})
	private List<ShipOwner> shipOwners;
	
	public Trader() {
		stays = new ArrayList<>();
		shipOwners = new ArrayList<>();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDefunctDate() {
		return defunctDate;
	}

	public void setDefunctDate(Date defunctDate) {
		this.defunctDate = defunctDate;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public List<Stay> getStays() {
		return stays;
	}

	public void setStays(List<Stay> stays) {
		this.stays = stays;
	}

	public List<ShipOwner> getShipOwner() {
		return shipOwners;
	}

	public void setShipOwner(List<ShipOwner> shipOwner) {
		this.shipOwners = shipOwner;
	}

	@Override
	public String toString() {
		return "Trader [id=" + id + ", nickName=" + nickname + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", startDate=" + startDate + ", defunctDate=" + defunctDate + "]";
	}
	
	public Stay getLastStay() {
		//SELECT * FROM stays WHERE stays.TraderId = 53 AND stays.EndDate IS NOT NULL ORDER BY stays.EndDate DESC LIMIT 1;
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Stay> lastStays = session.createQuery("from Stay as stay WHERE stay.trader.id = "+ this.getId() +" AND stay.endDate IS NOT NULL ORDER BY stay.endDate DESC").getResultList();
		session.getTransaction().commit();
		Stay lastStay = null;
		if(lastStays.size() != 0) {
			lastStay = lastStays.get(0);
		}
		return lastStay;
	}
	
	public void saveTrader() throws SQLException{
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(this);
		session.getTransaction().commit();
	}
	
	public List<Ship> getTraderShips() {
		List<Ship> shipsOfTrader = new ArrayList<>();
		List<ShipOwner> shipOwners = this.getShipOwner();
		for(ShipOwner actualShipOwner : shipOwners) {
			if(actualShipOwner.getLostDate() == null) {
				shipsOfTrader.add(actualShipOwner.getShip());
			}
		}
		return shipsOfTrader;
	}
	
}
