package controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import model.Planet;
import model.SolarSystem;

@ManagedBean
@ViewScoped
public class ChosePlanet {
	
	private List<SolarSystem> solarSystems = null;
	private List<Planet> planets = new ArrayList<Planet>();
	private SolarSystem selectedSolarSystem = null;
	private Planet selectedPlanet = null;
	
	public ChosePlanet() {
		solarSystems = SolarSystem.getAllSolarSystems();
	}
	
	public void setSolarSystem(AjaxBehaviorEvent e) {
		System.out.println("selectedSolarSystem " + selectedSolarSystem);
	}
	
	public void setSelectedSolarSystem(SolarSystem selectedSolarSystem) {
		this.selectedSolarSystem = selectedSolarSystem;
	}

	public void retrivePlanets(AjaxBehaviorEvent e) {
		System.out.println("SelectedSolarSystem-> " + selectedSolarSystem);
		if(selectedSolarSystem != null)
			this.planets = selectedSolarSystem.getPlanets();
		System.out.println("planets" + planets);
	}
	
	public List<Planet> getPlanets(){
		return planets;
	}
	
	public List<SolarSystem> getSolarSystems() {
		return solarSystems;
	}
	
	public SolarSystem getSelectedSolarSystem() {
		return selectedSolarSystem;
	}
	
	public Planet getSelectedPlanet() {
		return selectedPlanet;
	}

	public void setSelectedPlanet(Planet selectedPlanet) {
		this.selectedPlanet = selectedPlanet;
	}
}
