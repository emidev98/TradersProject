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
	
	public ChosePlanet() {
		solarSystems = SolarSystem.getAllSolarSystems();
	}
	
	public List<SolarSystem> getSolarSystems() {
		return solarSystems;
	}
	
	public void getPlanets(SolarSystem solarSystem) {
		System.out.println(solarSystem.toString());
	}
	
}
