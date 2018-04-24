package controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.Trader;

@ManagedBean
@SessionScoped
public class MainState implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date actualDate;
	private Trader trader;
	
	public MainState() {
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
    	Map<String, Object> requestMap = external.getSessionMap();
    	trader = (Trader) requestMap.get("trader");
    	actualDate = trader.getStartDate();
    	Calendar c = Calendar.getInstance();
    	c.setTime(actualDate);
    	c.add(Calendar.DATE, 1);
    	actualDate = c.getTime();
	}

	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

	public Trader getTrader() {
		return trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}
	
	public String retireTrader() {
		return "finishtravel.xhtml";
	}
}
