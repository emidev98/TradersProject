package controller;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.HibernateUtil;
import sakila_online.Customer;

@ManagedBean
@ViewScoped
public class CustomerController {
	public String byId(Customer customerPattern) {
		try {
			int id = customerPattern.getId();
			SessionFactory factory = HibernateUtil.getInstance().getSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Customer customer = session.get(Customer.class, id);
			session.getTransaction().commit();
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("customer", customer);	
			
		} catch (Exception exc) {
			return null;
		}
				
		return "response.xhtml";
	}
}
