package model;

import java.sql.SQLException;
import java.util.Date;

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
@Table(name = "ShipOwners")
public class ShipOwner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "ShipId")
	private Ship ship;
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "TraderId")
	private Trader trader;
	
	@Column(name = "AdquisitionDate")
	private Date adquisitionDate;
	
	@Column(name = "AdquisitionCause")
	private String adquisitionCause;
	
	@Column(name = "AdquisitionPrice")
	private double adquisitionPrice;
	
	@Column(name = "LostDate")
	private Date lostDate;
	
	@Column(name = "LostBenefit")
	private double lostBenefit;
	
	@Column(name = "LostCause")
	private String lostCause;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public Trader getTrader() {
		return trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

	public Date getAdquisitionDate() {
		return adquisitionDate;
	}

	public void setAdquisitionDate(Date adquisitionDate) {
		this.adquisitionDate = adquisitionDate;
	}

	public String getAdquisitionCause() {
		return adquisitionCause;
	}

	public void setAdquisitionCause(String adquisitionCause) {
		this.adquisitionCause = adquisitionCause;
	}

	public double getAdquisitionPrice() {
		return adquisitionPrice;
	}

	public void setAdquisitionPrice(double adquisitionPrice) {
		this.adquisitionPrice = adquisitionPrice;
	}

	public Date getLostDate() {
		return lostDate;
	}

	public void setLostDate(Date lostDate) {
		this.lostDate = lostDate;
	}

	public double getLostBenefit() {
		return lostBenefit;
	}

	public void setLostBenefit(double lostBenefit) {
		this.lostBenefit = lostBenefit;
	}

	public String getLostCause() {
		return lostCause;
	}

	public void setLostCause(String lostCause) {
		this.lostCause = lostCause;
	}
	
	
	@Override
	public String toString() {
		return "ShipOwner [ship=" + ship + ", trader=" + trader + ", adquisitionDate=" + adquisitionDate
				+ ", adquisitionCause=" + adquisitionCause + ", adquisitionPrice=" + adquisitionPrice + "]";
	}
	
	public static ShipOwner getShipOwnerById(int id) {
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		ShipOwner ship = session.get(ShipOwner.class, id);
		session.getTransaction().commit();
		return ship;
	}

	public void saveShipOwner() throws SQLException{
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(this);
		session.getTransaction().commit();
	}
	
	public void updateShipOwner() throws SQLException {
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.update(this);
		session.getTransaction().commit();
	}
	
}
