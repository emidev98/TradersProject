package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import model.Good;

@ManagedBean
@SessionScoped
public class Goods implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<Good> goods;
	private Good good;
	private String goodId;
	
	public void onGoodChangeListener(AjaxBehaviorEvent event) {
		System.out.println("onListener -> " + goodId);
	}
	
	//Getters, setters and constructor
	public Goods() {
		goods = Good.getAllGoods();
	}

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	public Good getGood() {
		return good;
	}

	public void setGood(String goodId) {
		this.goodId = goodId;
		System.out.println("good -> " + goodId);
	}

	
}
