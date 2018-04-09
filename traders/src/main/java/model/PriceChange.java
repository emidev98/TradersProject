package model;

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

@ManagedBean
@SessionScoped
@Entity
@Table(name = "PriceChanges")
public class PriceChange {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Date")
	private LocalDate date;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="PlanetId")
	private Planet planet;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="GoodId")
	private Good good;
	
	@Column(name = "NewPrice")
	private double newPrice;
	
	public PriceChange() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Planet getPlanet() {
		return planet;
	}
	public void setPlanet(Planet planet) {
		this.planet = planet;
	}
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public double getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}
	
}
