package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@ManagedBean
@SessionScoped
@Entity
@Table(name = "SolarSystemsDistances")
public class SolarSystemsDistance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	//TODO: Preguntar al joan sobre aixo
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.ALL})
	@JoinTable(name="SolarSystemsDistances", joinColumns=@JoinColumn(name="Id"),
			inverseJoinColumns=@JoinColumn(name="SolarSystemId1"))
	private SolarSystem system1;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.ALL})
	@JoinTable(name="SolarSystemsDistances", joinColumns=@JoinColumn(name="Id"),
			inverseJoinColumns=@JoinColumn(name="SolarSystemId2"))
	private SolarSystem system2;
	
	@Column(name = "Distance")
	private double distance;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SolarSystem getSystem1() {
		return system1;
	}

	public void setSystem1(SolarSystem system1) {
		this.system1 = system1;
	}

	public SolarSystem getSystem2() {
		return system2;
	}

	public void setSystem2(SolarSystem system2) {
		this.system2 = system2;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	
}
