package fr.upem.jee.allodoc.entity;

import com.google.common.base.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

/**
 * Created by Sirpapy on 14/12/2016.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "findPatientFirstnameLastName",
                query = "SELECT p from Patient p where p.firstName = :pFirstName and p.lastName = :pLastName"),

})
public class Patient extends User implements Serializable {
    @OneToMany(cascade = {CascadeType.ALL})
    List<Appointment> appointments;

    public Patient() {
        super();
    }

    public Patient(String firstName, String lastName, String email, String phoneNumber, Address address, String password) {
        super(firstName, lastName, phoneNumber, email, password, address);
    }

    public void addAppointment(Appointment newAppointment) {
        Preconditions.checkNotNull(newAppointment, "newAppointment should not be null");
        appointments.add(newAppointment);
    }

    public void removeAppointment(Appointment a) {
        appointments.remove(a);
    }

    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private String password;
        private Address address;
        private Date birthDate;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Patient build() {
            Patient patient = new Patient(firstName, lastName, email, phoneNumber, address, password);
            patient.setBirthDate(birthDate);
            return patient;
        }
    }
}
