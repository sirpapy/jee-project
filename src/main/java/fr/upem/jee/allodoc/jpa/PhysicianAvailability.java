package fr.upem.jee.allodoc.jpa;

import com.google.common.base.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
@Table(name = "physician_availability")
@NamedQuery(name = "getPhysicianAvailabilies",
query = "select p.id.availability from PhysicianAvailability p where p.id.physician.id = :pId")
public class PhysicianAvailability implements Serializable {
    @EmbeddedId
    private PhysicianAvailabilityId id;

    @OneToOne
    private Appointment appointment;

    public PhysicianAvailability() {
    }

    public PhysicianAvailability(Physician physician, Availability availability){
        Preconditions.checkNotNull(physician, "physician should not be null");
        Preconditions.checkNotNull(availability, "availability should not be null");
        this.id = new PhysicianAvailabilityId();
        id.setPhysician(physician);
        id.setAvailability(availability);
    }

    public PhysicianAvailabilityId getId() {
        return id;
    }

    public void setId(PhysicianAvailabilityId id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Physician getPhysician() {
        return id.getPhysician();
    }

    public void setPhysician(Physician physician) {
        this.id.physician = physician;
    }

    public Availability getAvailability() {
        return id.getAvailability();
    }

    public void setAvailability(Availability availability) {
        this.id.availability = availability;
    }

    /**
     * Composite id for physician_availability
     */
    @Embeddable
    public static class PhysicianAvailabilityId implements Serializable {
        @ManyToOne
        private Physician physician;

        @ManyToOne
        private Availability availability;

        public PhysicianAvailabilityId() {
        }

        public Physician getPhysician() {
            return physician;
        }

        public void setPhysician(Physician physician) {
            this.physician = physician;
        }

        public Availability getAvailability() {
            return availability;
        }

        public void setAvailability(Availability availability) {
            this.availability = availability;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PhysicianAvailabilityId that = (PhysicianAvailabilityId) o;
            return Objects.equals(physician, that.physician) &&
                    Objects.equals(availability, that.availability);
        }

        @Override
        public int hashCode() {
            return Objects.hash(physician, availability);
        }


    }
}
