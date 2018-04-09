package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.SolarSystem;

@FacesConverter("solarSystemConverter")
public class SolarSystemConverter implements Converter{

    @Override
    public SolarSystem getAsObject(FacesContext fc, UIComponent uic, String string){
    	int id = Integer.parseInt(string);
    	System.out.println("SolarSystemByID -> " + SolarSystem.getSolarSystemById(id));
    	return SolarSystem.getSolarSystemById(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object obj){
    	return obj.toString();
    }
}
