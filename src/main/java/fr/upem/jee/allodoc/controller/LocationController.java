package fr.upem.jee.allodoc.controller;

import fr.upem.jee.allodoc.entity.Location;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by raptao on 12/14/2016.
 */
public class LocationController extends Controller<Location>{

    public LocationController() {
        super();
    }

    public Location getByPostalCode( int postalCode ){
        TypedQuery<Location> findByPostalCode = manager().getEntityManager().createNamedQuery("findByPostalCode", Location.class);
        findByPostalCode.setParameter("pc", postalCode);
        List<Location> resultList = findByPostalCode.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public Location add(Integer postalCode, String city, String country){
        Location location = getByPostalCode(postalCode);
        if ( location == null ){
            Location newLocation = new Location.Builder()
                    .setPostalCode(postalCode)
                    .setCity(city)
                    .setCountry(country).build();
            save(newLocation);
            return newLocation;
        }
        return location;
    }
}
