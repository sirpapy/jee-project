package fr.upem.jee.allodoc.jpa;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
public class Physician extends User {

    @OneToOne
    private FieldOfActivity fieldOfActivity;

    @OneToOne
    private Location practiceArea;

    @OneToOne
    private Availability availability;

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

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
}
