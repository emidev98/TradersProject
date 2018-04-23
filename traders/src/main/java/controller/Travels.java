package controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import model.Planet;
import model.SolarSystem;
import model.Stay;
import model.Trader;

@ManagedBean
@ViewScoped
public class Travels implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<SolarSystem> solarSystems;
	private Date endDate;
	private List<Planet> planets;
	private SolarSystem solarSystem;
	private String solarSystemId;
	private String planetId;
	private Date lastDate;
	private Trader trader;
	private Stay lastStay;
	private Calendar c = Calendar.getInstance();
		
	public void onSolarSystemChange(AjaxBehaviorEvent event) {
    	int id = Integer.parseInt(solarSystemId);
    	solarSystem = SolarSystem.getSolarSystemById(id);
    	planets = solarSystem.getPlanets();
	}
	public String createTravel() {
		Stay newStay = new Stay();
    	if (!planetId.equals("")) {
			int planetID = Integer.parseInt(planetId);
	    	Planet planet = Planet.getPlanetById(planetID);
	    	lastStay.setEndDate(endDate);  	
	    	newStay.setPlanet(planet);
	    	newStay.setTrader(trader);
	    	int days = lastStay.getPlanet().getTime(planet);
	    	c.setTime(endDate);
	    	c.add(Calendar.DATE, days);
	    	newStay.setStartDate(c.getTime());
	    	newStay.setEndDate(null);
	    	trader.getStays().add(newStay);
	    	try {
				lastStay.updateStay();
				//newStay.saveStay();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
		return "mainstate.xhtml";
	}
	
	public void findTrader() {
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
    	Map<String, Object> requestMap = external.getSessionMap();
    	trader = (Trader) requestMap.get("trader");
	}
	
	public void findLastStay() {
		for (Stay stay: trader.getStays()) {
    		if (stay.getEndDate() == null)
    			lastStay = stay;
    	}
	}
	
	public Travels() {
		solarSystems = SolarSystem.getAllSolarSystems();
		findTrader();
		findLastStay();
    	c.setTime(lastStay.getStartDate());
    	c.add(Calendar.DATE, 1);
		lastDate = c.getTime();
	}
	
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
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
