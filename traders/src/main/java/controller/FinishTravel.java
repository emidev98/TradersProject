package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.ShipOwner;
import model.Trader;

@ManagedBean
@ViewScoped
public class FinishTravel {
	private MainState mainState;
	private Trader actualTrader;
	private String typeOfSell;
	private String choosenShip;
	private double price;
	private ShipOwner actualShipOwner;
	private boolean ultim = false;
	
	public FinishTravel() {
		List<ShipOwner> shipsToSell;
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = external.getSessionMap();
		actualTrader = (Trader) requestMap.get("trader");
    	mainState = (MainState) requestMap.get("mainState");
       	shipsToSell = actualTrader.getTraderShips();
    	actualShipOwner = shipsToSell.get(0);
    	if(shipsToSell.size() == 1) {
    		ultim = true;
    	}
	}
	
	public String sellShip() {
		actualShipOwner.setLostCause(typeOfSell);
		actualShipOwner.setLostDate(mainState.getActualDate());
		actualShipOwner.setLostBenefit(price);
		try {
			actualShipOwner.updateShipOwner();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(ultim) {
			return "bye.xhtml";
		}
		return "finishtravel.xhtml";
	}
	
	public Trader getActualTrader() {
		return actualTrader;
	}

	public void setActualTrader(Trader actualTrader) {
		this.actualTrader = actualTrader;
	}

	public String getTypeOfSell() {
		return typeOfSell;
	}

	public void setTypeOfSell(String typeOfSell) {
		this.typeOfSell = typeOfSell;
	}

	public String getChoosenShip() {
		return choosenShip;
	}

	public void setChoosenShip(String choosenShip) {
		this.choosenShip = choosenShip;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ShipOwner getActualShipOwner() {
		return actualShipOwner;
	}

	public void setActualShipOwner(ShipOwner actualShipOwner) {
		this.actualShipOwner = actualShipOwner;
	}
	
	
}
