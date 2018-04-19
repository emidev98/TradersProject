package controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.Ship;
import model.Trader;

@ManagedBean
@SessionScoped
public class InitialShip {
	private static final long serialVersionUID = 1L;
	
	private Trader actualTrader;
	private List<Ship> availableShips;
	private Ship choosenShip;
	
	public InitialShip() {
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = external.getSessionMap();
		actualTrader = (Trader) requestMap.get("trader");
		availableShips = Ship.getAvailableShips(actualTrader.getStartDate());
		availableShips = Ship.getShipsByType(Collections.unmodifiableList(availableShips), "Cargo ship");
	}
	
	public String create(Ship ship) {
		this.choosenShip = ship;
		
		return "main_state.xhtml";
	}

	public List<Ship> getAvailableShips() {
		return availableShips;
	}

	public void setAvailableShips(List<Ship> availableShips) {
		this.availableShips = availableShips;
	}

	public Ship getChoosenShip() {
		return choosenShip;
	}

	public void setChoosenShip(Ship choosenShip) {
		this.choosenShip = choosenShip;
	}
	
	
	
	
}
