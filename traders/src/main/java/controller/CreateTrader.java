package controller;

import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Trader;

@ManagedBean
@ViewScoped
public class CreateTrader {
	
	public CreateTrader() {

	}
		
	public String create(Trader trader) {
		try {
			trader.saveTrader();	
		} catch(SQLException e) {
			
		}
			
		return "choseplanet.xhtml";
	}
	
}
