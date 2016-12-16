package fr.upem.jee.allodoc.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
@NamedQuery(name = "getPhysicianFromId",
query = "SELECT p from Physician p where p.id = :pId")
public class Physician extends User implements Serializable {

    @OneToOne
    private FieldOfActivity fieldOfActivity;

    @OneToOne
    private Location practiceArea;

    @OneToMany
    private Set<PhysicianAvailability> availabilities;

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

}
