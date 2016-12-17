                        package fr.upem.jee.allodoc.jpa;

import javax.persistence.OneToOne;

/**
 * Created by raptao on 12/14/2016.
 */
public class Physician extends User {

    @OneToOne
    private FieldOfActivity fielOfActivity;

    public FieldOfActivity getFielOfActivity() {
        return fielOfActivity;
    }

    public void setFielOfActivity(FieldOfActivity fieldOfActivity) {
        this.fielOfActivity = fieldOfActivity;
    }

    public Physician(String firstName, String lastName){
        super(firstName, lastName);
    }
}