package fr.upem.jee.allodoc.entity;

import com.google.common.base.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
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

    public Patient(String firstName, String lastName, Address address, Account account) {
        super(firstName, lastName, address, account);
    }


    public void addAppointment(Appointment newAppointment) {
        Preconditions.checkNotNull(newAppointment, "newAppointment should not be null");
        if (appointments == null) {
            appointments = new ArrayList<>();
        }
        appointments.add(newAppointment);
    }

    public void removeAppointment(Appointment a) {
        appointments.remove(a);
    }

    public Collection<Appointment> getAppointments() {
        if (appointments == null) {
            appointments = new ArrayList<>();
        }
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private Address address;
        private Date birthDate;
        private Account account;

        public Builder setAccount(Account account) {
            this.account = account;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }


        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }


        public Builder setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Patient build() {
            Patient patient = new Patient(firstName, lastName, address, account);
            patient.setBirthDate(birthDate);
            return patient;
        }

        public Builder setRole(String role) {
            if (account == null) {
                throw new IllegalStateException("an account has to be set before role");
            }
            this.account.addRole(new Role(role));
            return this;
        }
    }
}
