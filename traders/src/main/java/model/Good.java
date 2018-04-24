package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.HibernateUtil;

@ManagedBean
@SessionScoped
@Entity
@Table(name = "Goods")
public class Good {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Name")
	private String name;
	
	public Good() {
		
	}
	
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
	public Map<Planet, Double> getPrices(LocalDate date){
		TreeMap<Planet, Double> prices = new TreeMap<>();
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<PriceChange> priceChanges = session.createQuery("from PriceChange pc "
				+ "WHERE pc.GoodId = " + this.getId() 
				+ " AND pc.Date = ( "
					+ "SELECT MAX(pcs.Date)"
						+ " FROM PriceChange pcs"
						+ " WHERE pcs.GoodId = " + this.getId()
						+ " AND pcs.PlanetId = pc.PlanetId"
						+ " AND pcs.Date <= \"" + date.toString() + "\");").getResultList();
		session.getTransaction().commit();
		for (PriceChange pc: priceChanges) {
			prices.put(pc.getPlanet(), pc.getNewPrice());
		}
		return prices;
	}
	/*
	 * SELECT *
	 * 	FROM PriceChanges pc
	 * 	WHERE pc.GoodId = 1
	 * 	AND pc.Date = (
	 * 		SELECT MAX(pcs.Date)
	 * 			FROM PriceChanges pcs
	 * 			WHERE pcs.GoodId = 1
	 * 			AND pcs.PlanetId = pc.PlanetId
	 * 			AND pcs.Date <= "2216-05-01");
	 */
}
