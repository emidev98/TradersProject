package controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import model.Planet;
import model.SolarSystem;
import model.Stay;
import model.Trader;

@ManagedBean
@SessionScoped
public class ChosePlanet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<SolarSystem> solarSystems;
	private List<Planet> planets;
	private SolarSystem solarSystem;
	private String solarSystemId;
	private String planetId;
	
	public void onSolarSystemChange(AjaxBehaviorEvent event) {
    	int id = Integer.parseInt(solarSystemId);
    	solarSystem = SolarSystem.getSolarSystemById(id);
    	planets = solarSystem.getPlanets();
	}
	
	public String createStay() {
    	int planetID = Integer.parseInt(planetId);
    	Planet planet = Planet.getPlanetById(planetID);
    	ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
    	Map<String, Object> requestMap = external.getSessionMap();
    	Trader t = (Trader) requestMap.get("trader");
    	Stay initStay = new Stay();
    	initStay.setPlanet(planet);
    	initStay.setTrader(t);
    	initStay.setStartDate(t.getStartDate());
    	initStay.setEndDate(null);
    	t.getStays().add(initStay);
    	try {
			initStay.saveStay();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return "mainstate.xhtml";
	}
	
	// Getters, Setters and Constructor

	public ChosePlanet() {
		solarSystems = SolarSystem.getAllSolarSystems();
	}
	
	public String getSolarSystemId() {
		return solarSystemId;
	}

	public void setSolarSystemId(String solarSystemId) {
		this.solarSystemId = solarSystemId;
	}

	public List<SolarSystem> getSolarSystems() {
		return solarSystems;
	}

	public void setSolarSystems(List<SolarSystem> solarSystems) {
		this.solarSystems = solarSystems;
	}

	public List<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}

	public SolarSystem getSolarSystem() {
		return solarSystem;
	}

	public void setSolarSystem(SolarSystem solarSystem) {
		this.solarSystem = solarSystem;
	}

	public String getPlanetId() {
		return planetId;
	}

	public void setPlanetId(String planetId) {
		this.planetId = planetId;
	}
}
