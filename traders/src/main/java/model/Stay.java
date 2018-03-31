package model;

import java.sql.SQLException;
import java.time.LocalDate;

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
@Table(name = "Stays")
public class Stay {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "StartDate")
	private LocalDate startDate;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="TraderId")
	private Trader trader;
	
	@Column(name = "EndDate")
	private LocalDate endDate;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "PlanetId")
	private Planet planet;
	
	public void saveStay() throws SQLException{
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(this);
		session.getTransaction().commit();
	}
}
