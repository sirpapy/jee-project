package fr.upem.jee.allodoc.jpa;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by raptao on 12/14/2016.
 */
@Embeddable
public class PhysicianAvailability implements Serializable {

    @ManyToOne
    private Physician physician;

    @ManyToOne
    private Availability availability;

    public PhysicianAvailability() {
    }

    @OneToOne
    private Appointment appointment;

    public Appointment getAppointment() {
        return appointment;
    }
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
