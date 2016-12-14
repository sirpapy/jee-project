package fr.upem.jee.allodoc.jpa;

import javax.persistence.OneToOne;

/**
 * Created by raptao on 12/14/2016.
 */
public class Physician extends User {

    @OneToOne
    private FieldOfActivity fieldOfActivity;

    public FieldOfActivity getFieldOfActivity() {
        return fieldOfActivity;
    }

    public void setFieldOfActivity(FieldOfActivity fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }

    public Physician(){

    }
}
