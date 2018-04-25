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
import model.ShipOwner;
import model.Trader;

@ManagedBean
@SessionScoped
public class InitialShip {
	
	private Trader actualTrader;
	private List<Ship> availableShips;
	private String choosenShip;
	private String typeOfBuy;
	private MainState mainState;
	
	public InitialShip() {
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = external.getSessionMap();
		actualTrader = (Trader) requestMap.get("trader");
		availableShips = Ship.getAvailableShips(actualTrader.getStartDate());
		availableShips = Ship.getShipsByType(Collections.unmodifiableList(availableShips), "Cargo ship");
	}
	
	public String getShip() {
		ShipOwner initShip = new ShipOwner();
		Ship choosenShip = Ship.getShipById(Integer.parseInt(this.choosenShip));
		initShip.setShip(choosenShip);
		initShip.setTrader(actualTrader);
		initShip.setAdquisitionDate(actualTrader.getStartDate());
		int price = 0;
		if(typeOfBuy.equalsIgnoreCase("Bought")) {
			price = choosenShip.getPrice();
		}
		initShip.setAdquisitionCause(typeOfBuy);
		initShip.setAdquisitionPrice(price);
		initShip.setLostCause("");
		initShip.setLostDate(null);
		initShip.setLostBenefit(0);
		System.out.println(initShip);
		
		try {
			initShip.saveShipOwner();
		} catch (SQLException e) {
			;
		}
		actualTrader.getShipOwner().add(initShip);
		return "mainstate.xhtml";
	}

	public List<Ship> getAvailableShips() {
		return availableShips;
	}

	public void setAvailableShips(List<Ship> availableShips) {
		this.availableShips = availableShips;
	}

	public String getChoosenShip() {
		return choosenShip;
	}

	public void setChoosenShip(String choosenShip) {
		this.choosenShip = choosenShip;
	}

	public String getTypeOfBuy() {
		return typeOfBuy;
	}

	public void setTypeOfBuy(String typeOfBuy) {
		this.typeOfBuy = typeOfBuy;
	}
	
	
	
	
	
}
