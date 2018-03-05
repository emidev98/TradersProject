package controller;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.HibernateUtil;
import model.Trader;

@ManagedBean
@ViewScoped
public class CreateTrader {
	public String create(Trader trader) {
		try {
			SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();

			session.getTransaction().commit();
			
//			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
//			Map<String, Object> requestMap = externalContext.getRequestMap();
//			requestMap.put("customer", customer);	
			
		} catch (Exception exc) {
			return null;
		}
				
		return "choseplanet.xhtml";
	}
}
