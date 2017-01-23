package fr.upem.jee.allodoc.service;

import fr.upem.jee.allodoc.DatabaseManager;
import fr.upem.jee.allodoc.entity.Location;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by raptao on 12/14/2016.
 */
public class LocationService extends Service<Location> {

    public LocationService() {
        super();
    }

    public Optional<Location> getByPostalCode(int postalCode ){
        TypedQuery<Location> findByPostalCode = manager().getEntityManager().createNamedQuery("findByPostalCode", Location.class);
        findByPostalCode.setParameter("pc", postalCode);
        List<Location> resultList = findByPostalCode.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    public Location add(Integer postalCode, String city, String country){
        Optional<Location> location = getByPostalCode(postalCode);
        if ( !location.isPresent() ){
            Location newLocation = new Location.Builder()
                    .setPostalCode(postalCode)
                    .setCity(city)
                    .setCountry(country).build();
            save(newLocation);
            return newLocation;
        }
        return location.get();
    }

    public static Location getByNamedArea(String regionName) {
        Objects.requireNonNull(regionName);
        DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();
        TypedQuery<Location> findLocationByRegion = databaseManager.getEntityManager().createNamedQuery("findLocationByRegion", Location.class);
        findLocationByRegion.setParameter("region", regionName);
        List<Location> resultList = findLocationByRegion.getResultList();
        return resultList.isEmpty() ? new Location.Builder().setCity(regionName).build() : resultList.get(0);
    }
}
