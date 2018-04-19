package controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
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
public class Travels implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<SolarSystem> solarSystems;
	private Date endDate;
	private List<Planet> planets;
	private SolarSystem solarSystem;
	private String solarSystemId;
	private String planetId;
	
	public void onSolarSystemChange(AjaxBehaviorEvent event) {
    	int id = Integer.parseInt(solarSystemId);
    	solarSystem = SolarSystem.getSolarSystemById(id);
    	planets = solarSystem.getPlanets();
	}
	public String createTravel() {
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
    	Map<String, Object> requestMap = external.getSessionMap();
    	Trader trader = (Trader) requestMap.get("trader");
    	Stay lastStay = null;
    	Stay newStay = new Stay();
    	int planetID = Integer.parseInt(planetId);
    	Planet planet = Planet.getPlanetById(planetID);
    	for (Stay stay: trader.getStays()) {
    		if (stay.getEndDate() == null)
    			lastStay = stay;
    	}
    	lastStay.setEndDate(endDate);  	
    	newStay.setPlanet(planet);
    	newStay.setTrader(trader);
    	int days = lastStay.getPlanet().getTime(planet);
    	//TODO aburrirte a pasar days a data
    	try {
			lastStay.saveStay();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		return "mainstate.xhtml";
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public String getSolarSystemId() {
		return solarSystemId;
	}
	public void setSolarSystemId(String solarSystemId) {
		this.solarSystemId = solarSystemId;
	}
	public String getPlanetId() {
		return planetId;
	}
	public void setPlanetId(String planetId) {
		this.planetId = planetId;
	}
	public List<SolarSystem> getSolarSystems() {
		return solarSystems;
	}
	public void setSolarSystems(List<SolarSystem> solarSystems) {
		this.solarSystems = solarSystems;
	}
	
}
