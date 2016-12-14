package fr.upem.jee.allodoc.jpa;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS)
public class Physician extends User {

    @OneToOne
    private FieldOfActivity fieldOfActivity;

    @OneToOne
    private Location practiceArea;

    @OneToOne
    private Availability disponibility;

    public FieldOfActivity getFieldOfActivity() {
        return fieldOfActivity;
    }

    public void setFieldOfActivity(FieldOfActivity fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }

    public Physician(){

    }
}
