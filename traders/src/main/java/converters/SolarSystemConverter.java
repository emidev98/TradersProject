package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("solarSystemConverter")
public class SolarSystemConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string){
    	Integer.parseInt(string);
    	return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object obj){
    	return obj.toString();
    }
}
