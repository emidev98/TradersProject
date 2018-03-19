package model;

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
@Table(name = "SolarSystems")
public class SolarSystem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Code")
	private String code;
	
	@Column(name = "Name")
	private String name;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="system", cascade={CascadeType.ALL})
	private List<Planet> planets;
	
	public SolarSystem() {
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
	
	public List<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}

	@Override
	public String toString() {
		return "SolarSystem [id=" + id + ", code=" + code + ", name=" + name + ", planets=" + planets + "]";
	}

	public static List<SolarSystem> getAllSolarSystems() {
		SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<SolarSystem> solarSystems = session.createQuery("from SolarSystem").getResultList();
		session.getTransaction().commit();
		return solarSystems;
	}
	
}
