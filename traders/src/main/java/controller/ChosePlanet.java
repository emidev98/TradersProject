package controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import model.Planet;
import model.SolarSystem;

@ManagedBean
@ViewScoped
public class ChosePlanet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<SolarSystem> solarSystems;
	private List<Planet> planets;
	private SolarSystem solarSystem;
	private Planet planet;
	private String solarSystemId;
	private String planetId;
	
	public void onSolarSystemChange(AjaxBehaviorEvent event) {
    	int id = Integer.parseInt(solarSystemId);
    	solarSystem = SolarSystem.getSolarSystemById(id);
    	planets = solarSystem.getPlanets();
	}
	
	public void createStay() {
    	int planetID = Integer.parseInt(planetId);
    	Planet planet = Planet.getPlanetById(planetID);
    	
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
	
	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}

	public String getPlanetId() {
		return planetId;
	}

	public void setPlanetId(String planetId) {
		this.planetId = planetId;
	}
}
