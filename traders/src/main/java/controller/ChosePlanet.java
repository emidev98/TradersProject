package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Planet;
import model.SolarSystem;

@ManagedBean
@ViewScoped
public class ChosePlanet {
	
	public List<SolarSystem> solarSystems = null;
	public List<Planet> planets = null;
	
	public ChosePlanet() {
		solarSystems = SolarSystem.getAllSolarSystems();	
	}
	
	public void getPlanets(SolarSystem solarSystem) {
		System.out.println(solarSystem.toString());
	}
	
}
