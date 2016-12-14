package fr.upem.jee.allodoc.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
public class Physician extends User implements Serializable {

    @OneToOne
    private FieldOfActivity fieldOfActivity;

    @OneToOne
    private Location practiceArea;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Availability> availabilities;

    public Physician() {

    }

    public FieldOfActivity getFieldOfActivity() {
        return fieldOfActivity;
    }

    public void setFieldOfActivity(FieldOfActivity fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }

    public Location getPracticeArea() {
        return practiceArea;
    }

    public void setPracticeArea(Location practiceArea) {
        this.practiceArea = practiceArea;
    }

    public Set<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailability(Availability availability) {
        this.availabilities.add(availability);
    }
}
