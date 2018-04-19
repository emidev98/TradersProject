package controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MainState implements Serializable{
	private static final long serialVersionUID = 1L;
	private String disabled = "disabled";
	
	public String getDisabled() {
		return disabled;
	}
	
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
}
