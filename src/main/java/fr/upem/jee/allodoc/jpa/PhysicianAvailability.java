package fr.upem.jee.allodoc.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by raptao on 12/14/2016.
 */
@Entity
@Table(name = "physician_availability")
//@NamedNativeQuery(name = "getPhysicianAvailabilites",
//query = "select * from phy")
public class PhysicianAvailability implements Serializable {
    @EmbeddedId
    private PhysicianAvailabilityId id;

    @OneToOne
    private Appointment appointment;

    public PhysicianAvailability() {
    }
    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }


    /**
     * Composite id for physician_availability
     */
    @Embeddable
    static class PhysicianAvailabilityId implements Serializable{
        private Long physicianId;
        private Long availavilityId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PhysicianAvailabilityId that = (PhysicianAvailabilityId) o;
            return Objects.equals(physicianId, that.physicianId) &&
                    Objects.equals(availavilityId, that.availavilityId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(physicianId, availavilityId);
        }
    }
}
