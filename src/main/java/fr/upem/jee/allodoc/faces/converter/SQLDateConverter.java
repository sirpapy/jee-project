package fr.upem.jee.allodoc.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.sql.Date;
import java.util.Objects;

/**
 * Created by raptao on 1/21/2017.
 */
@FacesConverter("dateConverter")
public class SQLDateConverter implements Converter{
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Objects.requireNonNull(value);
        System.out.println("DATE = "+value);
        return Date.valueOf(value);

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Objects.requireNonNull(value);
        Date valueAsDate = (Date)value;
        return valueAsDate.toString();
    }
}
