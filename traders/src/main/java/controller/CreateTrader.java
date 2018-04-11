package controller;

import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Trader;

@ManagedBean
@SessionScoped
public class CreateTrader {
	
	private Trader trader;
	
	public CreateTrader() {

	}
		
	public String create(Trader trader) {
		try {
			this.trader = trader;
			trader.saveTrader();	
		} catch(SQLException e) {
			
		}
			
		return "choseplanet.xhtml";
	}

	public Trader getTrader() {
		return trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}
	
}
