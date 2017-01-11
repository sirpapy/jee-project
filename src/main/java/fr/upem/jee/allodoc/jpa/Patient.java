package fr.upem.jee.allodoc.jpa;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Sirpapy on 14/12/2016.
 */

@Entity
@Table(name = "patient")
public class Patient extends User{

    @OneToMany
    Collection<Appointment> appointments =  new ArrayList<>();

    public Patient() {
        super();
    }

    public Patient(String firstName, String lastName, String email, String phoneNumber, Address address, String password) {
        super(firstName,lastName,phoneNumber,email,password,address);
    }
    public void addAppointment(Appointment a){
        appointments.add(a);
    }
    public void removeAppointment(Appointment a){
        appointments.remove(a);
    }
    public Collection<Appointment> getAppointments(){
        return appointments;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private Address address;
        private String password;

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

        public Patient build() {
            return new Patient(firstName, lastName, email, phoneNumber, address, password);
        }
    }
}
