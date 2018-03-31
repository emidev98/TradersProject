package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Planet;
import model.SolarSystem;

@ManagedBean
@ViewScoped
public class ChosePlanet {
	
	private List<SolarSystem> solarSystems = null;
	private List<Planet> planets = null;
	private SolarSystem selectedSolarSystem = null;
	private Planet selectedPlanet = null;
	

	public Planet getSelectedPlanet() {
		return selectedPlanet;
	}

	public void setSelectedPlanet(Planet selectedPlanet) {
		this.selectedPlanet = selectedPlanet;
	}

	public ChosePlanet() {
		solarSystems = SolarSystem.getAllSolarSystems();
		System.out.println(solarSystems);
	}
	
	public void setSolarSystems(List<SolarSystem> solarSystems) {
		this.solarSystems = solarSystems;
	}

	public void setSelectedSolarSystem(SolarSystem selectedSolarSystem) {
		this.selectedSolarSystem = selectedSolarSystem;
	}

	public List<SolarSystem> getSolarSystems() {
		return solarSystems;
	}
	
	public List<Planet> getPlanets(SolarSystem solarSystem) {
		return solarSystem.getPlanets();
	}
	
	public List<Planet> getPlanets() {
		return planets;
	}
	
	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}

	public SolarSystem getSelectedSolarSystem() {
		return selectedSolarSystem;
	}
	
}
