package fr.upem.jee.allodoc.jpa;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
@Table(name = "physician_availability")
public class PhysicianAvailability implements Serializable {

    @EmbeddedId
    private PhysicianAvailabilityKey id;

    @OneToOne
    private Appointment appointment;

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public PhysicianAvailability() {

    }
}
