package controller;

import java.io.Serializable;
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
public class Ships implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Ship> shipsToBuy;
	private List<Ship> shipsToSell;
	private Trader actualTrader;
	
	public Ships() {
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = external.getSessionMap();
		actualTrader = (Trader) requestMap.get("trader");
		
	}
}
