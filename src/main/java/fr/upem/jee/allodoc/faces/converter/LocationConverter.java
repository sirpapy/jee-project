package fr.upem.jee.allodoc.faces.converter;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.service.LocationService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.Optional;

/**
 * Created by raptao on 1/21/2017.
 */
@FacesConverter("locationConverter")
public class LocationConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Preconditions.checkNotNull(value, "locationString should not be null");
        String[] splitValue = value.split("-");
        int postalCode = Integer.parseInt(splitValue[0]);
        String city = splitValue[1];
        LocationService locationService = new LocationService();
        Optional<Location> byPostalCode = locationService.getByPostalCode(postalCode);
        if (byPostalCode.isPresent()) {
            return byPostalCode.get();
        }
        Location location = new Location(postalCode, city);
        locationService.save(location);
        return location;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if( value == null){
            return null;
        }
        Location valueAsLocation = (Location) value;
        return valueAsLocation.getPostalCode() + "-" + valueAsLocation.getCity();
    }
}
