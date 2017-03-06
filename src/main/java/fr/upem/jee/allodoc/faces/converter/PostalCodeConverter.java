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
 * Created by raptao on 2/28/2017.
 */
@FacesConverter("postalCodeConverter")
public class PostalCodeConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Preconditions.checkNotNull(value, "postalCodeString should not be null");
        LocationService locationService = new LocationService();
        int postalCode = Integer.parseInt(value);
        Optional<Location> byPostalCode = locationService.getByPostalCode(postalCode);
        if (byPostalCode.isPresent()) {
            return byPostalCode.get();
        }
        Location location = new Location.Builder().setPostalCode(postalCode).build();
        locationService.save(location);
        return location;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if( value == null ){
            return null;
        }
        Location asLocation = (Location)value;
        return asLocation.getPostalCode().toString();
    }
}
