package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Planet;
import model.SolarSystems;

@ManagedBean
@ViewScoped
public class ChosePlanet {
	
	private List<SolarSystems> solarSystems = null;
	private List<Planet> planets = null;
	
	public ChosePlanet() {
		solarSystems = SolarSystems.getAllSolarSystems();	
	}
	
	public void getPlanets(SolarSystems solarSystem) {
		System.out.println(solarSystem.toString());
	}
	
}
