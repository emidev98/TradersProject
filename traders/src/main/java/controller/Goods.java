package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import model.Planet;
import model.SolarSystem;

@ManagedBean
@SessionScoped
public class Goods implements Serializable{
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
	
	// Getters, Setters and Constructor
	public Goods() {
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
