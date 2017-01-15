package fr.upem.jee.allodoc.jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Sirpapy on 14/12/2016.
 */

@Entity
@NamedQueries({

        @NamedQuery(name = "getPatientnFromId",
                query = "SELECT p from Patient p where p.id = :pId"),

        @NamedQuery(name = "findPatientFirstnameLastName",
                query = "SELECT p from Patient p where p.firstName = :pFirstName and p.lastName = :pLastName"),

})
@Table(name = "patient")
public class Patient extends User{

    @OneToMany(cascade = {CascadeType.ALL})
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
}
