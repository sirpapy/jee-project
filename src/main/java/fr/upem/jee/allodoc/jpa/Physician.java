package fr.upem.jee.allodoc.jpa;

import com.google.common.base.Preconditions;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private List<PhysicianAvailability> availabilities;

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

    public void setAvailability(Availability availability) {
        Preconditions.checkNotNull(availability);
        if( availabilities == null){
            availabilities = new ArrayList<>();
        }
        this.availabilities.add(new PhysicianAvailability(this, availability));
    }

    public List<PhysicianAvailability> getAvailabilities() {
        return availabilities;
    }

}
