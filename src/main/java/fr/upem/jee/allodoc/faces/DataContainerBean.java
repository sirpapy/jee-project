package fr.upem.jee.allodoc.faces;

import fr.upem.jee.allodoc.entity.FieldOfActivity;
import fr.upem.jee.allodoc.entity.Location;
import fr.upem.jee.allodoc.service.FieldOfActivityService;
import fr.upem.jee.allodoc.service.LocationService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by raptao on 2/24/2017.
 */
@ManagedBean
@ApplicationScoped
public class DataContainerBean {
    public static final String VALUE_NOT_SET = "<not set>";
    public static final int NOT_SET = 0;

    private List<FieldOfActivity> fieldOfActivities;
    private List<Location> locations;

    public DataContainerBean() {
        fieldOfActivities = FieldOfActivityService.getAll();
        locations = LocationService.getAll().stream().distinct().collect(Collectors.toList());
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<FieldOfActivity> getFieldOfActivities() {
        return fieldOfActivities;
    }

    public void setFieldOfActivities(List<FieldOfActivity> fieldOfActivities) {
        this.fieldOfActivities = fieldOfActivities;
    }

    public FieldOfActivity getUnSetFieldOfActivity() {
        return new FieldOfActivity(VALUE_NOT_SET);
    }

    public Location getUnSetPostalCode() {
        return new Location.Builder()
                .setCity(VALUE_NOT_SET)
                .setCountry(VALUE_NOT_SET)
                .setPostalCode(0)
                .build();

    }
}
