package controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import model.Good;

@ManagedBean
@SessionScoped
public class Goods implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<Good> goods;
	private Good good;
	private String goodId;
	private Date fromDate;
	
	public void onGoodChangeListener(AjaxBehaviorEvent event) {
		for (Good good : goods) {
			if(good.getId() == Integer.parseInt(goodId)) {
				this.good = good;
			}
		}
		System.out.println("onListener -> " + good);
	}
	
	public void onDateSelectedListener(SelectEvent event) {
		System.out.println(fromDate);
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

	public void setGood(Good good) {
		this.good = good;
	}

	public String getGoodId() {
		return goodId;
	}

	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	
}
