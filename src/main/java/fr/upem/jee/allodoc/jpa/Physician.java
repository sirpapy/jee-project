package fr.upem.jee.allodoc.jpa;

import com.google.common.base.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
@NamedQueries({

        @NamedQuery(name = "getPhysicianFromId",
                query = "SELECT p from Physician p where p.id = :pId"),

        @NamedQuery(name = "findPhysicianFirstnameLastName",
                query = "SELECT p from Physician p where p.firstName = :pFirstName and p.lastName = :pLastName")
})

public class Physician extends User implements Serializable {

    @OneToOne
    private FieldOfActivity fieldOfActivity;

    @OneToOne
    private Location practiceArea;
    @OneToMany
    private List<PhysicianAvailability> physicianAvailability;

    public Physician() {
    }

    public List<PhysicianAvailability> getPhysicianAvailability() {
        return physicianAvailability;
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

    public void setAvailability(Availability availability) {
        Preconditions.checkNotNull(availability);
        if (physicianAvailability == null) {
            physicianAvailability = new ArrayList<>();
        }
        this.physicianAvailability.add(new PhysicianAvailability(this, availability));
    }

    public List<Availability> getAvailabilities() {
        return physicianAvailability.stream().map(PhysicianAvailability::getAvailability).collect(Collectors.toList());
    }

}
