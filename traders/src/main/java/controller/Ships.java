package controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import model.Ship;
import model.ShipOwner;
import model.Trader;

@ManagedBean
@ViewScoped
public class Ships implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Ship> shipsToBuy;
	private List<Ship> shipsToSell;
	private Trader actualTrader;
	private String typeOfBuy;
	private String typeOfSell;
	private Date lastStayDate;
	private Date managmentDate;
	private Date managmentDateSell;
	private String choosenShip;
	private String choosenShipSell;
	private double price;
	private double priceSell;
	private MainState mainState;
	
	public Ships() {
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = external.getSessionMap();
		actualTrader = (Trader) requestMap.get("trader");
    	mainState = (MainState) requestMap.get("mainState");
    	shipsToSell = actualTrader.getTraderShips();
    	lastStayDate = actualTrader.getLastStay().getEndDate();
	}
	
	public void onDateSelect(SelectEvent event) {
		System.out.println(managmentDate);
		shipsToBuy = Ship.getAvailableShips(managmentDate);
		int i = 0;
		for(Ship actualShip : shipsToBuy) {
			System.out.println(actualShip);
			i++;
		}
		System.out.println(i);
	}
	
	public String buyShip(){
		ShipOwner newShipOwner = new ShipOwner();
		Ship choosenShip = Ship.getShipById(Integer.parseInt(this.choosenShip));
		newShipOwner.setShip(choosenShip);
		newShipOwner.setTrader(actualTrader);
		newShipOwner.setAdquisitionDate(managmentDate);
		newShipOwner.setAdquisitionCause(typeOfBuy);
		newShipOwner.setAdquisitionPrice(price);
		newShipOwner.setLostCause("");
		newShipOwner.setLostBenefit(0);
		newShipOwner.setLostDate(null);
		actualTrader.getShipOwner().add(newShipOwner);
		mainState.setActualDate(managmentDate);
		try {
			newShipOwner.saveShipOwner();
		} catch (SQLException e) {
			;
		}
		return "mainstate.xhtml";
	}

	public List<Ship> getShipsToBuy() {
		return shipsToBuy;
	}

	public void setShipsToBuy(List<Ship> shipsToBuy) {
		this.shipsToBuy = shipsToBuy;
	}

	public List<Ship> getShipsToSell() {
		return shipsToSell;
	}

	public void setShipsToSell(List<Ship> shipsToSell) {
		this.shipsToSell = shipsToSell;
	}

	public Trader getActualTrader() {
		return actualTrader;
	}

	public void setActualTrader(Trader actualTrader) {
		this.actualTrader = actualTrader;
	}

	public String getTypeOfBuy() {
		return typeOfBuy;
	}

	public void setTypeOfBuy(String typeOfBuy) {
		this.typeOfBuy = typeOfBuy;
	}

	public Date getManagmentDate() {
		return managmentDate;
	}

	public void setManagmentDate(Date managmentDate) {
		this.managmentDate = managmentDate;
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

	public String getChoosenShipSell() {
		return choosenShipSell;
	}

	public void setChoosenShipSell(String choosenShipSell) {
		this.choosenShipSell = choosenShipSell;
	}

	public double getPriceSell() {
		return priceSell;
	}

	public void setPriceSell(double priceSell) {
		this.priceSell = priceSell;
	}

	public Date getManagmentDateSell() {
		return managmentDateSell;
	}

	public void setManagmentDateSell(Date managmentDateSell) {
		this.managmentDateSell = managmentDateSell;
	}

	public String getTypeOfSell() {
		return typeOfSell;
	}

	public void setTypeOfSell(String typeOfsell) {
		this.typeOfSell = typeOfsell;
	}

	public Date getLastStayDate() {
		return lastStayDate;
	}

	public void setLastStayDate(Date lastStayDate) {
		this.lastStayDate = lastStayDate;
	}
	
	
}
