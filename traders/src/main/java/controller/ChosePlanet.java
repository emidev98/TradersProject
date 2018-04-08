package controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	
	public ChosePlanet() {
		solarSystems = SolarSystem.getAllSolarSystems();
	}
	
	public void onSolarSystemChange() {
		System.out.println("solarSystemChange");
		planets = solarSystem.getPlanets();
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
}
