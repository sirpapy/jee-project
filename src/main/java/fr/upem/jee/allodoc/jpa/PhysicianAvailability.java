package fr.upem.jee.allodoc.jpa;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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

    @OneToMany
    private Set<Appointment> appointments;

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public PhysicianAvailability() {

    }
}
